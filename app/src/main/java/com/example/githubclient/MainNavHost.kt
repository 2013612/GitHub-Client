package com.example.githubclient

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.githubclient.user.presentation.UserGraph
import com.example.githubclient.user.presentation.userGraph

@Composable
fun MainNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = UserGraph,
        modifier = modifier,
    ) {
        userGraph(navController = navHostController)
    }
}
