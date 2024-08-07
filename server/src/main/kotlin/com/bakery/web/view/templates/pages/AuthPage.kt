package com.bakery.web.view.templates.pages

import kotlinx.html.FlowContent
import kotlinx.html.div
import kotlinx.html.h1

fun FlowContent.loginPage() = div {
    h1 {
        +"login"
    }
}

fun FlowContent.signUpPage() = div {
    h1 {
        +"sign up"
    }
}
