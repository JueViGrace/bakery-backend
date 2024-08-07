package com.bakery.web.router

import com.bakery.web.templates.components.headerAuthMenu
import com.bakery.web.templates.components.headerProfileMenu
import com.bakery.web.templates.pages.contactPage
import com.bakery.web.templates.pages.homePage
import com.bakery.web.templates.pages.shopPage
import com.bakery.web.templates.respondFullPage
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.html.body

fun Route.webRoutes() {
    route("/") {
        get {
            call.respondFullPage {
                homePage()
            }
        }

        get("/contact") {
            call.respondFullPage {
                contactPage()
            }
        }

        get("/shop") {
            call.respondFullPage {
                shopPage()
            }
        }

        components()
    }
}

fun Route.components() {
    route("/components") {
        get("/home") {
        }

        get("/contact") {
        }

        get("/shop") {
        }

        get("/auth") {
            call.respondHtml {
                body {
                    if (true) {
                        headerProfileMenu()
                    } else {
                        headerAuthMenu()
                    }
                }
            }
        }
    }
}
