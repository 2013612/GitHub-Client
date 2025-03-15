package com.example.githubclient.user.presentation.detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import com.example.githubclient.R
import com.example.githubclient.theme.GitHubClientTheme
import com.example.githubclient.user.domain.model.UserProfile

@Composable
fun UserDetailProfile(
    profile: UserProfile,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = profile.avatarUrl,
                contentDescription = stringResource(R.string.avatar_content_desc, profile.userName),
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
            Column {
                profile.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
                Text(
                    text = profile.userName,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = Icons.Default.Person, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.num_followers, profile.followers))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = stringResource(R.string.num_following, profile.following))
        }

        profile.company?.let {
            DetailRow(painter = painterResource(R.drawable.baseline_domain_24), text = it)
        }

        profile.location?.let {
            DetailRow(icon = Icons.Default.LocationOn, text = it)
        }

        profile.email?.let {
            DetailRow(icon = Icons.Default.Email, text = it)
        }

        profile.blog?.let {
            DetailRow(painter = painterResource(R.drawable.baseline_link_24), text = it)
        }

        profile.twitterUsername?.let {
            DetailRow(painter = painterResource(R.drawable.x_twitter_brands_solid), text = it)
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
    painter: Painter,
    text: String,
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painter, contentDescription = null, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text)
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview(backgroundColor = 0xFFFFFF)
@Composable
private fun UserDetailProfilePreview() {
    val previewHandler =
        AsyncImagePreviewHandler {
            ColorImage(Color.Red.toArgb())
        }

    GitHubClientTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            UserDetailProfile(
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
            )
        }
    }
}
