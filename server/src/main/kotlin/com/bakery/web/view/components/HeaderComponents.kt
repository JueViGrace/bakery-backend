package com.bakery.web.view.components

import com.bakery.web.view.components.NavigationMenu.Contact
import com.bakery.web.view.components.NavigationMenu.Home
import com.bakery.web.view.components.NavigationMenu.Shop
import kotlinx.html.FlowContent
import kotlinx.html.a
import kotlinx.html.button
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.header
import kotlinx.html.id
import kotlinx.html.img
import kotlinx.html.nav
import kotlinx.html.p
import kotlinx.html.section

fun FlowContent.pageHeader() = section {
    classes = setOf("w-full", "bg-gray-100")
    header {
        classes = setOf(
            "container",
            "mx-auto",
            "flex",
            "flex-row",
            "justify-between",
            "items-center",
            "p-2"
        )

        headerIcon()

        div {
            classes = setOf("flex", "flex-row", "justify-center", "items-center", "gap-6")

            headerNavMenu()

            div {
                attributes["preload"] = "true"
                attributes["hx-get"] = "/components/auth"
                attributes["hx-trigger"] = "load"
                attributes["hx-swap"] = "outerHTML"
                attributes["hx-target"] = "#auth"

                headerAuthMenu()
            }
        }
    }
}

fun FlowContent.headerIcon() = div {
    classes = setOf("p-2")
    button {
        a {
            href = "/"
            img {
                attributes["preload"] = "true"
                classes = setOf("size-8")
                src = "/favicon-32x32.png"
                alt = "Logo"
            }
        }
    }
}

fun FlowContent.headerNavMenu() = nav {
    classes = setOf("flex", "flex-row", "justify-between", "items-center", "gap-4", "p-2")
    listOf(
        Home,
        Shop,
        Contact
    ).forEach { item ->
        div {
            classes = setOf("h-full", "px-2", "hover:text-red-300")
            button {
                a {
                    href = "/${item.name}"

                    +item.displayName
                }
            }
        }
    }
}

fun FlowContent.headerAuthMenu() = div {
    id = "auth"
    classes = setOf("flex", "flex-row", "justify-center", "items-center", "gap-4")

    a {
        href = "/login"
        button {
            classes = setOf(
                "border",
                "rounded",
                "p-2",
                "hover:bg-red-300/50"
            )

            p {
                +"Log in"
            }
        }
    }
    a {
        href = "/signup"
        button {
            classes = setOf(
                "text-gray-100",
                "border",
                "rounded",
                "bg-red-400",
                "p-2",
                "hover:bg-red-500",
            )

            p {
                +"Sign up"
            }
        }
    }
}

fun FlowContent.headerProfileMenu() = div {
    button {
        classes = setOf("border", "rounded-full", "hover:border-red-300")
        img {
            attributes["preload"] = "true"
            classes = setOf("size-8", "border", "rounded-full")
            src = "/favicon-32x32.png"
            alt = "Logo"
        }
    }
}

sealed class NavigationMenu(
    val name: String,
    val displayName: String,
) {
    data object Home : NavigationMenu(
        name = "",
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
