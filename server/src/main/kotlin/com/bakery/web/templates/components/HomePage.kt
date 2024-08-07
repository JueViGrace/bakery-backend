package com.bakery.web.templates.components

import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h1

fun FlowContent.homePage() = div {
    h1 {
        +"Home page"
    }
}
