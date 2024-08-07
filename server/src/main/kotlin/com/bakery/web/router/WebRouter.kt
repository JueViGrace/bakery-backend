package com.bakery.web.router

import com.bakery.web.templates.components.contactPage
import com.bakery.web.templates.components.headerAuthMenu
import com.bakery.web.templates.components.homePage
import com.bakery.web.templates.components.shopPage
import com.bakery.web.templates.index
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.css.body
import kotlinx.css.p
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.p

fun Route.webRoutes() {
    route("/") {
        get {
            call.respondHtml {
                index()
            }
        }

        components()
    }
}

fun Route.components() {
    route("/components") {
        get("/home") {
            call.respondHtml {
                body {
                    homePage()
                }
            }
        }

        get("/contact") {
            call.respondHtml {
                body {
                    contactPage()
                }
            }
        }

        get("/shop") {
            call.respondHtml {
                body {
                    shopPage()
                }
            }
        }

        get("/auth") {
            call.respondHtml {
                body {
                    if (false) {
                        headerAuthMenu()
                    } else {
                        div {
                            p {
                                +"hola"
                            }
                        }
                    }
                }
            }
        }
    }
}
