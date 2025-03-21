package com.example.githubclient.common.data.util

import com.example.githubclient.common.data.model.DataError
import com.example.githubclient.common.domain.model.ResultWrapper
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.serialization.SerializationException
import kotlin.coroutines.cancellation.CancellationException

suspend inline fun <reified T> safeCall(execute: () -> HttpResponse): ResultWrapper<T, DataError.Remote> {
    val response =
        try {
            execute()
        } catch (e: UnresolvedAddressException) {
            e.printStackTrace()
            return ResultWrapper.Error(DataError.Remote.NO_INTERNET)
        } catch (e: HttpRequestTimeoutException) {
            e.printStackTrace()
            return ResultWrapper.Error(DataError.Remote.REQUEST_TIMEOUT)
        } catch (e: SerializationException) {
            e.printStackTrace()
            return ResultWrapper.Error(DataError.Remote.SERIALIZATION)
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            return ResultWrapper.Error(DataError.Remote.UNKNOWN)
        }

    return response.toResult()
}
