package com.bakery.server

import com.bakery.database.driver.DriverFactory.configureDatabase
import com.bakery.server.config.configureHTTP
import com.bakery.server.config.configureKoin
import com.bakery.server.config.configureMonitoring
import com.bakery.server.config.configureRouting
import com.bakery.server.config.configureSecurity
import com.bakery.server.config.configureSerialization
import com.bakery.server.config.configureValidation
import com.bakery.server.router.appRoutes
import com.bakery.web.client.KtorClient.configureClient
import com.bakery.web.config.configureTemplating
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    // Basic configuration
    configureKoin()
    configureSecurity()
    configureDatabase()
    configureClient()
    configureRouting()
    configureValidation()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureTemplating()

    // Application routes
    appRoutes()
}
