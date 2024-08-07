package com.bakery.web.templates.components

import kotlinx.html.FlowContent
import kotlinx.html.button
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.p

fun FlowContent.headerAuthMenu() =
    div {
        classes = setOf("flex", "flex-row", "justify-center", "items-center", "gap-4")
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
