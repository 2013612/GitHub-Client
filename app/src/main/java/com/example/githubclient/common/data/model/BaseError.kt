package com.example.githubclient.common.data.model

interface BaseError

sealed interface DataError : BaseError {
    enum class Remote : DataError {
        BAD_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        REQUEST_TIMEOUT,
        CONFLICT,
        PAYLOAD_TOO_LARGE,
        TOO_MANY_REQUESTS,
        SERVER_ERROR,
        NO_INTERNET,
        SERIALIZATION,
        UNKNOWN,
    }
}
