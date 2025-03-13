package com.example.githubclient.user.data

import com.example.githubclient.common.data.model.DataError
import com.example.githubclient.common.data.util.safeCall
import com.example.githubclient.common.domain.model.ResultWrapper
import com.example.githubclient.common.domain.model.map
import com.example.githubclient.user.data.mapper.toSimpleUser
import com.example.githubclient.user.data.model.RemoteSimpleUser
import com.example.githubclient.user.domain.IUserRepository
import com.example.githubclient.user.domain.model.SimpleUser
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class UserDataSource(
    val httpClient: HttpClient,
) : IUserRepository {
    override suspend fun fetchUserList(
        since: Int,
        perPage: Int,
    ): ResultWrapper<List<SimpleUser>, DataError> =
        safeCall<List<RemoteSimpleUser>> {
            httpClient.get("/users") {
                parameter("since", since)
                parameter("per_page", perPage)
            }
        }.map { remoteSimpleUsers -> remoteSimpleUsers.map { it.toSimpleUser() } }
}
