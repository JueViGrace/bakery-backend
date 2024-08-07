package com.bakery.server.config

import io.ktor.http.CacheControl.MaxAge
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.content.CachingOptions
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.cachingheaders.CachingHeaders
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.routing.routing

fun Application.configureHTTP() {
    install(CORS) {
        allowHeader(HttpHeaders.Authorization)
    }

    routing {
        install(CachingHeaders) {
            options { _, content ->
                when (content.contentType?.withoutParameters()) {
                    ContentType.Text.Plain -> CachingOptions(MaxAge(maxAgeSeconds = 24 * 60 * 60))
                    ContentType.Text.Html -> CachingOptions(MaxAge(maxAgeSeconds = 24 * 60 * 60))
                    ContentType.Text.CSS -> CachingOptions(MaxAge(maxAgeSeconds = 24 * 60 * 60))

                    else -> null
                }
            }
        }
    }
}
