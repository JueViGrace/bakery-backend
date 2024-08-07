package com.bakery.web.templates.content

import com.bakery.web.templates.components.headerAuthMenu
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.footer
import kotlinx.html.h1
import kotlinx.html.header
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.main
import kotlinx.html.nav
import kotlinx.html.section

fun FlowContent.mainPage() = section {
    classes = setOf("w-full", "h-full")

    main {
        classes = setOf("flex", "flex-grow", "flex-col", "justify-center", "items-center", "gap-2")

        attributes["hx-get"] = "/components/home"
        attributes["hx-target"] = "#pageContent"
        attributes["hx-trigger"] = "load"

        pageHeader()

        section {
            classes = setOf("container", "mx-auto", "px-2")
            div {
                id = "pageContent"
                attributes["hx-swap"] = "innerHTML"
            }
        }
    }

    pageFooter()
}

private fun FlowContent.pageHeader() = section {
    classes = setOf("w-full", "bg-gray-200")
    header {
        classes = setOf(
            "container", "mx-auto", "flex", "flex-row", "justify-between", "items-center", "p-2"
        )

        div {
            classes = setOf("p-2")
            button {
                a {
                    href = "/"
                    img {
                        classes = setOf("size-8")
                        src = "/favicon-32x32.png"
                        alt = "Logo"
                    }
                }
            }
        }

        div {
            classes = setOf("flex", "flex-row", "justify-center", "items-center", "gap-6")

            headerNavMenu()

            div {
                attributes["hx-get"] = "/components/auth"
                attributes["hx-trigger"] = "load"
                attributes["hx-swap"] = "outerHTML"
                attributes["hx-target"] = "this"
            }
        }
    }
}

private fun FlowContent.headerNavMenu() {
    nav {
        classes = setOf("flex", "flex-row", "justify-between", "items-center", "gap-4", "p-2")
        listOf(
            NavigationMenu.Home,
            NavigationMenu.Shop,
            NavigationMenu.Contact
        ).forEach { item ->
            div {
                classes = setOf("h-full", "px-2", "hover:text-red-300")
                button {
                    a {
                        attributes["hx-get"] = "/components/${item.name}"
                        attributes["hx-target"] = "#pageContent"
                        href = "#"

                        +item.displayName
                    }
                }
            }
        }
    }
}

private fun FlowContent.pageFooter() = footer {
    h1 {
        +"Footer"
    }
}

sealed class NavigationMenu(
    val name: String,
    val displayName: String,
) {
    data object Home : NavigationMenu(
        name = "home",
        displayName = "Home"
    )
    data object Shop : NavigationMenu(
        name = "shop",
        displayName = "Shop"
    )
    data object Contact : NavigationMenu(
        name = "contact",
        displayName = "Contact"
    )
}
