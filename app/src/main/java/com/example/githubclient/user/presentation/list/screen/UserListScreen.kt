package com.example.githubclient.user.presentation.list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.githubclient.R
import com.example.githubclient.common.presentation.model.PaginationState
import com.example.githubclient.theme.GitHubClientTheme
import com.example.githubclient.user.domain.model.SimpleUser
import com.example.githubclient.user.presentation.list.model.UserListScreenEvent
import com.example.githubclient.user.presentation.list.model.UserListScreenState
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.userListScreen(navigateToUserDetails: (String) -> Unit) {
    composable<NavUserListScreen> {
        val viewModel: UserListViewModel = koinViewModel()

        val screenState by viewModel.screenStateFlow.collectAsStateWithLifecycle()

        UserListScreen(
            state = screenState,
            onEvent = { event ->
                when (event) {
                    is UserListScreenEvent.OnUserClicked -> navigateToUserDetails(event.user)
                    else -> viewModel.onEvent(event)
                }
            },
        )
    }
}

@Serializable
object NavUserListScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreen(
    state: UserListScreenState,
    onEvent: (UserListScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val latestOnEvent by rememberUpdatedState(onEvent)
    val context = LocalContext.current

    val shouldStartPaginate =
        remember {
            derivedStateOf {
                state.paginationState == PaginationState.IDLE &&
                    (
                        lazyListState.layoutInfo.visibleItemsInfo
                            .lastOrNull()
                            ?.index ?: Int.MIN_VALUE
                    ) >= (lazyListState.layoutInfo.totalItemsCount - 20)
            }
        }

    LaunchedEffect(shouldStartPaginate.value) {
        if (shouldStartPaginate.value) {
            latestOnEvent(UserListScreenEvent.OnLoadMore)
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.github_user_list)) }) },
        modifier = modifier,
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = state.paginationState == PaginationState.LOADING,
            state = rememberPullToRefreshState(),
            onRefresh = {
                onEvent(UserListScreenEvent.OnRefresh)
            },
        ) {
            LazyColumn(
                state = lazyListState,
                modifier =
                    Modifier
                        .padding(innerPadding)
                        .fillMaxSize(),
            ) {
                items(state.users, key = { it.id }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier =
                            Modifier
                                .clickable {
                                    onEvent(UserListScreenEvent.OnUserClicked(it.userName))
                                }.padding(8.dp)
                                .fillMaxWidth(),
                    ) {
                        AsyncImage(
                            model = it.avatarUrl,
                            contentDescription =
                                context.getString(
                                    R.string.avatar_content_desc,
                                    it.userName,
                                ),
                            placeholder = painterResource(R.drawable.github_mark),
                            error = painterResource(R.drawable.github_mark),
                            fallback = painterResource(R.drawable.github_mark),
                            modifier =
                                Modifier
                                    .size(64.dp)
                                    .clip(
                                        CircleShape,
                                    ),
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(it.userName)
                    }
                    HorizontalDivider()
                }

                item(
                    key = state.paginationState,
                ) {
                    when (state.paginationState) {
                        PaginationState.LOADING -> {
                            Column(
                                modifier =
                                    Modifier
                                        .fillParentMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(
                                    modifier =
                                        Modifier
                                            .padding(8.dp),
                                    text = stringResource(R.string.refresh_loading),
                                )

                                CircularProgressIndicator()
                            }
                        }

                        PaginationState.PAGINATING -> {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = stringResource(R.string.pagination_loading))

                                CircularProgressIndicator()
                            }
                        }

                        PaginationState.PAGINATION_EXHAUST -> {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 6.dp, vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Face,
                                    contentDescription = null,
                                )

                                Text(text = stringResource(R.string.nothing_left))
                            }
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun UserListScreenPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    GitHubClientTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            UserListScreen(
                state =
                    UserListScreenState(
                        users =
                            listOf(
                                SimpleUser(
                                    id = 1,
                                    userName = "John Doe",
                                    avatarUrl = "https://example.com/avatar.png",
                                ),
                                SimpleUser(
                                    id = 2,
                                    userName = "Jane Doe",
                                    avatarUrl = "https://example.com/avatar.png",
                                ),
                            ),
                        paginationState = PaginationState.PAGINATION_EXHAUST,
                    ),
                onEvent = {},
            )
        }
    }
}
