package com.example.githubclient.user.data

import com.example.githubclient.common.data.model.DataError
import com.example.githubclient.common.data.util.safeCall
import com.example.githubclient.common.domain.model.ResultWrapper
import com.example.githubclient.common.domain.model.map
import com.example.githubclient.user.data.mapper.toSimpleUser
import com.example.githubclient.user.data.mapper.toUserEvent
import com.example.githubclient.user.data.mapper.toUserProfile
import com.example.githubclient.user.data.model.RemoteSimpleUser
import com.example.githubclient.user.data.model.RemoteUserProfile
import com.example.githubclient.user.data.model.event.RemotePublicUserEvent
import com.example.githubclient.user.domain.IUserRepository
import com.example.githubclient.user.domain.model.SimpleUser
import com.example.githubclient.user.domain.model.UserProfile
import com.example.githubclient.user.domain.model.event.UserEvent
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

    override suspend fun fetchUserProfile(userName: String): ResultWrapper<UserProfile, DataError> =
        safeCall<RemoteUserProfile> {
            httpClient.get("/users/$userName")
        }.map { it.toUserProfile() }

    override suspend fun fetchUserEvents(
        userName: String,
        page: Int,
        perPage: Int,
    ): ResultWrapper<List<UserEvent>, DataError> =
        safeCall<List<RemotePublicUserEvent>> {
            httpClient.get("/users/$userName/events") {
                parameter("page", page)
                parameter("per_page", perPage)
            }
        }.map { events ->
            events.mapNotNull { it.toUserEvent() }
        }
}
