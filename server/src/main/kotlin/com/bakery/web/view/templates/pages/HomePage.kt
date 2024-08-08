package com.bakery.web.view.templates.pages

import com.bakery.web.view.templates.components.contentContainer
import com.bakery.web.view.templates.mainPage
import com.bakery.web.view.templates.respondFullPage
import io.ktor.server.application.call
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h1

fun Route.home() {
    get("/") {
       call.respondFullPage {
           mainPage {
               homePage()
           }
       }
    }
}

fun FlowContent.homePage() = contentContainer {
    div {
        h1 {
            +"Home page"
        }
    }
}
