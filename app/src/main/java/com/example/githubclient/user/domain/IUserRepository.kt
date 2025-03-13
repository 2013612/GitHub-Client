package com.example.githubclient.user.domain

import com.example.githubclient.common.data.model.DataError
import com.example.githubclient.common.domain.model.ResultWrapper
import com.example.githubclient.user.domain.model.SimpleUser

interface IUserRepository {
    suspend fun fetchUserList(
        since: Int = 0,
        perPage: Int = 30,
    ): ResultWrapper<List<SimpleUser>, DataError>
}
