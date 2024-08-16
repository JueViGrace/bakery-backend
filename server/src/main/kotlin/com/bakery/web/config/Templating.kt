package com.bakery.web.config

import com.github.mustachejava.DefaultMustacheFactory
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.http.content.staticResources
import io.ktor.server.mustache.Mustache
import io.ktor.server.routing.routing

fun Application.configureTemplating() {
    install(Mustache) {
        mustacheFactory = DefaultMustacheFactory("templates")
    }

    routing {
        staticResources("/global.css", "static") {
            default("styles/global.css")
        }

        staticResources("/favicon-32x32.png", "static") {
            default("images/icon/favicon-32x32.png")
        }

        staticResources("/favicon-16x16.png", "static") {
            default("images/icon/favicon-16x16.png")
        }

        staticResources("/apple-touch-icon.png", "static") {
            default("images/icon/apple-touch-icon.png")
        }

        staticResources("/site.webmanifest", "static") {
            default("images/icon/site.webmanifest")
        }
    }
}
