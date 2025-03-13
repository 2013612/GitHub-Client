package com.example.githubclient.user.presentation.detail.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubclient.R
import com.example.githubclient.theme.GitHubClientTheme
import com.example.githubclient.user.domain.model.UserProfile
import com.example.githubclient.user.presentation.detail.model.UserDetailScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailScreen(
    state: UserDetailScreenState,
    modifier: Modifier = Modifier,
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text("User Detail") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "back button",
                )
            },
        )
    }, modifier = modifier) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).padding(horizontal = 8.dp)) {
            if (state.profile != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        imageVector = Icons.Rounded.Face,
                        contentDescription = null,
                        modifier = Modifier.size(72.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = state.profile.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                        )
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

@Preview
@Composable
private fun UserDetailScreenPreview() {
    GitHubClientTheme {
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
        )
    }
}
