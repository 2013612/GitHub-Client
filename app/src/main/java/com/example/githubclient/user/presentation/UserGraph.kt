package com.example.githubclient.user.presentation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.githubclient.user.presentation.detail.screen.NavUserDetailScreen
import com.example.githubclient.user.presentation.detail.screen.userDetailScreen
import com.example.githubclient.user.presentation.list.screen.NavUserListScreen
import com.example.githubclient.user.presentation.list.screen.userListScreen
import kotlinx.serialization.Serializable

@Serializable
object UserGraph

fun NavGraphBuilder.userGraph(navController: NavController) {
    navigation<UserGraph>(startDestination = NavUserListScreen) {
        userListScreen(navigateToUserDetails = {
            navController.navigate(NavUserDetailScreen(it))
        })

        userDetailScreen(navigateBack = {
            navController.navigateUp()
        })
    }
}
