package com.bakery.web.templates

import com.bakery.web.templates.components.pageFooter
import com.bakery.web.templates.components.pageHeader
import io.ktor.server.application.ApplicationCall
import io.ktor.server.html.respondHtml
import kotlinx.html.FlowContent
import kotlinx.html.HTML
import kotlinx.html.HtmlBlockTag
import kotlinx.html.body
import kotlinx.html.classes
import kotlinx.html.head
import kotlinx.html.lang
import kotlinx.html.link
import kotlinx.html.main
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.section
import kotlinx.html.title

suspend fun ApplicationCall.respondFullPage(e: HtmlBlockTag.() -> Unit) {
    respondHtml {
        layout {
            e()
        }
    }
}

fun HTML.layout(e: HtmlBlockTag.() -> Unit) {
    lang = "es"
    head {
        meta {
            name = "viewport"
            content = "width=device-width, initial-scale=1.0"
        }

        meta {
            charset = "utf-8"
        }

        script {
            src = "https://unpkg.com/htmx.org@2.0.0"
        }

        script {
            src = "https://unpkg.com/htmx-ext-preload@2.0.0/preload.js"
        }

        script {
            src = "https://cdn.tailwindcss.com"
        }

        link {
            rel = "stylesheet"
            href = "/global.css"
        }

        link {
            rel = "icon"
            type = "image/png"
            sizes = "32x32"
            href = "/favicon-32x32.png"
        }

        link {
            rel = "icon"
            type = "image/png"
            sizes = "16x16"
            href = "/favicon-16x16.png"
        }

        link {
            rel = "apple-touch-icon"
            sizes = "180x180"
            href = "/apple-touch-icon.png"
        }

        link {
            rel = "manifest"
            href = "/site.webmanifest"
        }

        title {
            +"Bakery and Deserts"
        }
    }

    body {
        attributes["hx-ext"] = "preload"
        mainPage {
            e()
        }
    }
}

fun FlowContent.mainPage(e: HtmlBlockTag.() -> Unit) = section {
    classes = setOf("flex", "flex-col", "w-auto", "min-h-screen", "justify-center")

    main {
        classes = setOf("flex", "flex-col", "grow", "justify-center", "items-center")

        pageHeader()

        section {
            classes = setOf("container", "mx-auto", "flex", "grow", "justify-center", "p-2")
            e()
        }
    }

    pageFooter()
}
