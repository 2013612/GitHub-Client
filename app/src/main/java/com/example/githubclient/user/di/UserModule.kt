package com.example.githubclient.user.di

import com.example.githubclient.user.data.UserDataSource
import com.example.githubclient.user.domain.IUserRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userModule =
    module {
        singleOf(::UserDataSource).bind<IUserRepository>()
    }
