package com.bakery.server.router

import com.bakery.api.router.apiRoutes
import com.bakery.web.view.router.webRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.routing

fun Application.appRoutes() {
    routing {
        apiRoutes()
        webRoutes()
    }
}
