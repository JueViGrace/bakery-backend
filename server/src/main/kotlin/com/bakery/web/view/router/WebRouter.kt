package com.bakery.web.view.router

import com.bakery.web.view.templates.pages.home
import com.bakery.web.view.templates.components.headerAuthMenu
import com.bakery.web.view.templates.components.headerProfileMenu
import com.bakery.web.view.templates.mainPage
import com.bakery.web.view.templates.pages.contactPage
import com.bakery.web.view.templates.pages.loginPage
import com.bakery.web.view.templates.pages.shopPage
import com.bakery.web.view.templates.pages.signUpPage
import com.bakery.web.view.templates.respondFullPage
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlinx.html.body

fun Route.webRoutes() {
    home()

    get("/contact") {
        call.respondFullPage {
            mainPage {
                contactPage()
            }
        }
    }

    get("/shop") {
        call.respondFullPage {
            mainPage {
                shopPage()
            }
        }
    }

    get("/login") {
        call.respondFullPage {
            loginPage()
        }
    }

    get("/signup") {
        call.respondFullPage {
            signUpPage()
        }
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
        call.respondHtml {
            body {
                if (false) {
                    headerProfileMenu()
                } else {
                    headerAuthMenu()
                }
            }
        }
    }
}
