package com.nameisjayant.plugins

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.Resources


fun Application.configureRouting() {
    install(Resources)

}

@Resource("/")
class Test