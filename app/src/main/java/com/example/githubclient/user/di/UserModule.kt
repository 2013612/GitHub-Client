package com.example.githubclient.user.di

import com.example.githubclient.common.data.manager.HttpManager
import com.example.githubclient.user.data.UserDataSource
import com.example.githubclient.user.domain.IUserRepository
import com.example.githubclient.user.presentation.detail.screen.UserDetailViewModel
import com.example.githubclient.user.presentation.list.screen.UserListViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule =
    module {
        single<HttpClient> {
            HttpManager.httpClient
        }
        singleOf(::UserDataSource).bind<IUserRepository>()
        viewModelOf(::UserListViewModel)
        viewModelOf(::UserDetailViewModel)
    }
