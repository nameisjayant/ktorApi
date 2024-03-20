package com.nameisjayant

import com.nameisjayant.base.BaseApplication
import com.nameisjayant.features.user.routes.configureUserRoutes
import com.nameisjayant.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8083, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    // configure koin
    configureKoin()

    val component = BaseApplication()
    val userRepository = component.userRepository

    // configure serialization/content negotiation
    configureSerialization()

    // configure logger
    configureMonitoring()

    // configure swagger for api documentation
    configureHTTP()

    // configure jwt token
    configureSecurity()

    // routes
    configureUserRoutes(userRepository)
}
