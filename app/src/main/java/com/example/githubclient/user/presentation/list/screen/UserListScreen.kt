package com.example.githubclient.user.presentation.list.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil3.compose.AsyncImage
import com.example.githubclient.theme.GitHubClientTheme
import com.example.githubclient.user.domain.model.SimpleUser
import com.example.githubclient.user.presentation.list.model.ListState
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

    val shouldStartPaginate =
        remember {
            derivedStateOf {
                state.listState == ListState.IDLE &&
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

    Scaffold(topBar = { TopAppBar(title = { Text("GitHub User List") }) }, modifier = modifier) { innerPadding ->
        PullToRefreshBox(isRefreshing = state.listState == ListState.LOADING, state = rememberPullToRefreshState(), onRefresh = {
            onEvent(UserListScreenEvent.OnRefresh)
        }) {
            LazyColumn(state = lazyListState, modifier = Modifier.padding(innerPadding).fillMaxSize()) {
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
                        AsyncImage(model = it.avatarUrl, contentDescription = null, modifier = Modifier.height(64.dp))
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(it.userName)
                    }
                    HorizontalDivider()
                }

                item(
                    key = state.listState,
                ) {
                    when (state.listState) {
                        ListState.LOADING -> {
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
                                    text = "Refresh Loading",
                                )

                                CircularProgressIndicator()
                            }
                        }
                        ListState.PAGINATING -> {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Text(text = "Pagination Loading")

                                CircularProgressIndicator()
                            }
                        }
                        ListState.PAGINATION_EXHAUST -> {
                            Column(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 6.dp, vertical = 16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                            ) {
                                Image(
                                    imageVector = Icons.Rounded.Face,
                                    contentDescription = "",
                                )

                                Text(text = "Nothing left.")
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserListScreenPreview() {
    GitHubClientTheme {
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
                    listState = ListState.PAGINATION_EXHAUST,
                ),
            onEvent = {},
        )
    }
}
