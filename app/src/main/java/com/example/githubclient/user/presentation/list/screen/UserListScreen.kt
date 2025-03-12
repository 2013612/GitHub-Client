package com.example.githubclient.user.presentation.list.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubclient.R
import com.example.githubclient.theme.GitHubClientTheme
import com.example.githubclient.user.presentation.list.model.UserListScreenEvent
import com.example.githubclient.user.presentation.list.model.UserListScreenState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserListScreen(
    state: UserListScreenState,
    onEvent: (UserListScreenEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(topBar = { TopAppBar(title = { Text("GitHub User List") }) }, modifier = modifier) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            items(state.users, key = { it }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier =
                        Modifier.padding(8.dp).clickable {
                            onEvent(UserListScreenEvent.OnUserClicked(it))
                        },
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_launcher_foreground),
                        contentDescription = null,
                        modifier = Modifier.height(32.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(it)
                }
                HorizontalDivider()
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
                    users = listOf("a", "b", "c"),
                ),
            onEvent = {},
        )
    }
}
