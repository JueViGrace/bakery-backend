package com.bakery.web.router

import com.bakery.models.product.ProductDto
import com.bakery.models.response.AppResponse
import com.bakery.models.response.AppResponse.FailureResponse
import com.bakery.models.response.AppResponse.SuccessResponse
import com.bakery.web.client.KtorClient
import com.bakery.web.view.components.products
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.server.application.call
import io.ktor.server.html.respondHtml
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.thymeleaf.respondTemplate
import kotlinx.html.body
import kotlinx.html.h1

fun Route.webRoutes() {
    get("/") {
        call.respondTemplate("index.html")
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
    get("/container") {
        call.respondTemplate("container.html")
    }

    get("/footer") {
        call.respondTemplate("footer.html")
    }

    get("/header") {
        call.respondTemplate("header.html")
    }

    get("/main") {
        call.respondTemplate("main.html")
    }

    route("/products") {
        get {
            val products =
                KtorClient.client()
                    .get(urlString = "/api/products")
                    .body<AppResponse<List<ProductDto>>>()


            when (products) {
                is FailureResponse -> call.respondHtml {
                    body {
                        h1 {
                            +"${products.message} ${products.description}"
                        }
                    }
                }
                is SuccessResponse -> {
                    call.respondHtml {
                        body {
                            products(products.body)
                        }
                    }
                }
            }
        }
    }
}
