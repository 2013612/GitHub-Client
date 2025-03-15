package com.example.githubclient.user.presentation.detail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.githubclient.common.presentation.utils.ObserveAsEvents
import com.example.githubclient.common.presentation.utils.showToast
import com.example.githubclient.theme.GitHubClientTheme
import com.example.githubclient.user.domain.model.UserProfile
import com.example.githubclient.user.domain.model.event.CommitCommentEvent
import com.example.githubclient.user.presentation.detail.component.UserDetailProfile
import com.example.githubclient.user.presentation.detail.model.UserDetailScreenEvent
import com.example.githubclient.user.presentation.detail.model.UserDetailScreenState
import com.example.githubclient.user.presentation.detail.model.UserDetailViewModelEvent
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

fun NavGraphBuilder.userDetailScreen(navigateBack: () -> Unit) {
    composable<NavUserDetailScreen> {
        val viewModel: UserDetailViewModel = koinViewModel()

        val state by viewModel.screenStateFlow.collectAsStateWithLifecycle()

        val context = LocalContext.current

        ObserveAsEvents(viewModel.eventsFlow) { event ->
            when (event) {
                is UserDetailViewModelEvent.OnFetchError -> {
                    if (event.isNoInternetError) {
                        context.showToast("No internet connection")
                    } else {
                        context.showToast("Fetch User Fail")
                    }

                    navigateBack()
                }
            }
        }

        UserDetailScreen(state = state, onEvent = { event ->
            when (event) {
                UserDetailScreenEvent.OnBackClicked -> navigateBack()
            }
        })
    }
}

@Serializable
data class NavUserDetailScreen(
    val userName: String,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailScreen(
    state: UserDetailScreenState,
    onEvent: (UserDetailScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("User Detail") },
            navigationIcon = {
                IconButton(onClick = { onEvent(UserDetailScreenEvent.OnBackClicked) }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back button",
                    )
                }
            },
        )
    }, modifier = modifier) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            state.profile?.let {
                item {
                    UserDetailProfile(profile = it, modifier = Modifier.padding(horizontal = 8.dp))
                }
            }

            item {
                HorizontalDivider(Modifier.padding(vertical = 8.dp))
            }

            items(state.events, key = { it.id }) { event ->
                EventRow(
                    date = event.getEventDate(),
                    time = event.getEventTime(),
                    eventDesc = event.getEventDesc(),
                    modifier = Modifier.padding(horizontal = 8.dp),
                )
            }
        }
    }
}

@Composable
private fun EventRow(
    date: String,
    time: String,
    eventDesc: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Text(text = date)
            Text(text = time)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = eventDesc)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
private fun UserDetailScreenPreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    GitHubClientTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            UserDetailScreen(
                state =
                    UserDetailScreenState(
                        profile =
                            UserProfile(
                                id = 1,
                                userName = "John Doe",
                                name = "John Doe",
                                avatarUrl = "https://example.com/avatar.png",
                                followers = 10,
                                following = 20,
                                company = "Example Inc.",
                                location = "Exampleville",
                                email = "james.monroe@examplepetstore.com",
                                blog = "blog",
                                twitterUsername = "twitterUsername",
                            ),
                        // Add more events as needed
                        events =
                            listOf(
                                CommitCommentEvent(
                                    id = "1",
                                    isoDateTime = "2025-03-13T00:49:56Z",
                                    commitId = "commitId 1",
                                    repoName = "repoName 1",
                                ),
                                CommitCommentEvent(
                                    id = "2",
                                    isoDateTime = "2025-03-13T00:49:54Z",
                                    commitId = "commitId 2",
                                    repoName = "repoName 2",
                                ),
                            ),
                    ),
                onEvent = {},
            )
        }
    }
}
