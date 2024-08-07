package com.bakery.web.view.templates.pages

import com.bakery.web.view.templates.components.contentContainer
import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h1

fun FlowContent.homePage() = contentContainer {
    div {
        h1 {
            +"Home page"
        }
    }
}
