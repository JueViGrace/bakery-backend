package com.bakery.web.templates

import com.bakery.web.templates.content.mainPage
import kotlinx.html.HTML
import kotlinx.html.body
import kotlinx.html.head
import kotlinx.html.lang
import kotlinx.html.link
import kotlinx.html.meta
import kotlinx.html.script
import kotlinx.html.title

fun HTML.index() {
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
            src = "https://unpkg.com/htmx.org@2.0.1"
            integrity = "sha384-QWGpdj554B4ETpJJC9z+ZHJcA/i59TyjxEPXiiUgN2WmTyV5OEZWCD6gQhgkdpB/"
            attributes["crossorigin"] = "anonymous"
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
        mainPage()
    }
}
