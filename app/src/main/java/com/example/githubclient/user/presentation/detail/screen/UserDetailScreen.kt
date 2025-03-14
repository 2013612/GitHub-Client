package com.example.githubclient.user.presentation.detail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
import com.example.githubclient.common.presentation.utils.ObserveAsEvents
import com.example.githubclient.common.presentation.utils.showToast
import com.example.githubclient.theme.GitHubClientTheme
import com.example.githubclient.user.domain.model.UserProfile
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
        Column(modifier = Modifier.padding(innerPadding).padding(horizontal = 8.dp)) {
            if (state.profile != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(
                        model = state.profile.avatarUrl,
                        contentDescription = null,
                        modifier =
                            Modifier.size(64.dp).clip(
                                CircleShape,
                            ),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        state.profile.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                        Text(
                            text = state.profile.userName,
                            style = MaterialTheme.typography.titleSmall,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${state.profile.followers} followers")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "${state.profile.following} following")
                }

                state.profile.company?.let {
                    DetailRow(icon = painterResource(R.drawable.baseline_domain_24), text = it)
                }

                state.profile.location?.let {
                    DetailRow(icon = Icons.Default.LocationOn, text = it)
                }

                state.profile.email?.let {
                    DetailRow(icon = Icons.Default.Email, text = it)
                }

                state.profile.blog?.let {
                    DetailRow(icon = painterResource(R.drawable.baseline_link_24), text = it)
                }

                state.profile.twitterUsername?.let {
                    DetailRow(icon = Icons.Default.Build, text = it)
                }
            }
        }
    }
}

@Composable
private fun DetailRow(
    icon: ImageVector,
    text: String,
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
    }
}

@Composable
private fun DetailRow(
    icon: Painter,
    text: String,
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(painter = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
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
                    ),
                onEvent = {},
            )
        }
    }
}
