package com.bakery.web.client

import com.bakery.models.response.AppResponse
import com.bakery.models.response.AppResponse.FailureResponse
import com.bakery.models.response.AppResponse.SuccessResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import kotlinx.serialization.json.Json

object KtorClient {
    private lateinit var BASE_URL: String
    private var env: Boolean = false

    fun Application.configureClient() {
        BASE_URL = environment.config.property("ktor.deployment.domain").getString()
        env = environment.config.property("ktor.environment").getString().toBoolean()
    }

    fun client() = HttpClient(CIO) {
        install(Logging) {
            logger = Logger.SIMPLE
            level = if (env) {
                LogLevel.ALL
            } else {
                LogLevel.INFO
            }

            sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(DefaultRequest) {
            url(BASE_URL)
        }
    }

    inline fun <T> safeApiCall(apiCall: () -> AppResponse<T>): AppResponse<T> {
        return try {
            when (val call = apiCall()) {
                is FailureResponse -> {
                    FailureResponse(
                        status = call.status,
                        description = call.description,
                        time = call.time,
                        message = call.message,
                        error = call.error,
                        path = call.path
                    )
                }
                is SuccessResponse -> {
                    SuccessResponse(
                        status = call.status,
                        description = call.description,
                        body = call.body!!,
                        message = call.message,
                    )
                }
            }
        } catch (e: Exception) {
            error(e)
        }
    }
}
