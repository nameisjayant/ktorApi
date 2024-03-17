package com.nameisjayant.plugins

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

private const val API = "api"

fun Application.configureRouting() {
    install(Resources)
}

@Serializable
@Resource("$API/user")
object UserRoute
