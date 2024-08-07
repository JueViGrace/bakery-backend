package com.bakery.web.templates.components

import kotlinx.html.FlowContent
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.footer
import kotlinx.html.h1

fun FlowContent.pageFooter() = footer {
    classes = setOf("w-full", "min-h-[100px]", "bg-red-300")

    div {
        classes = setOf("container", "mx-auto", "p-2")

        h1 {
            +"Footer"
        }
    }
}
