package com.bakery.web.router

import io.ktor.server.application.call
import io.ktor.server.mustache.respondTemplate
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.webRoutes() {
    get("/" ) {
        call.respondTemplate("index.hbs")
    }

    get("/contact") {

    }

    get("/shop") {

    }

    get("/login") {

    }

    get("/signup") {

    }

    route("/components") {
        components()
    }
}

fun Route.components() {
    get("/home") {
    }

    get("/contact") {
    }

    get("/shop") {
    }

    get("/auth") {

    }
}