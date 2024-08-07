package com.bakery.web.templates.components

import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h1

fun FlowContent.shopPage() = div {
    h1 {
        +"Shop page"
    }
}
