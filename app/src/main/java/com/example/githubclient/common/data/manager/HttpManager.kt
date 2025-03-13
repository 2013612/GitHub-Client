package com.example.githubclient.common.data.manager

import com.example.githubclient.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object HttpManager {
    private const val TIMEOUT = 60_000L
    val json =
        Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }

    val httpClient =
        HttpClient {
            install(HttpTimeout) {
                connectTimeoutMillis = TIMEOUT
                requestTimeoutMillis = TIMEOUT
            }
            install(ContentNegotiation) {
                json(
                    json,
                )
            }
            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            if (BuildConfig.DEBUG) {
                                println("HTTP call $message")
                            }
                        }
                    }
                level = LogLevel.ALL
            }

            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.github.com"
                }
                header("X-GitHub-Api-Version", "2022-11-28")
                header("Accept", "application/vnd.github+json")
                contentType(ContentType.Application.Json)
            }
        }
}
