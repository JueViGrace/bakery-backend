package com.bakery.web.templates.components

import kotlinx.html.FlowContent
import kotlinx.html.HtmlBlockTag
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.id

fun FlowContent.contentContainer(c: HtmlBlockTag.() -> Unit) = div {
    id = "content-container"
    classes = setOf("w-auto", "flex", "flex-col", "flex-start", "grow")

    c()
}
