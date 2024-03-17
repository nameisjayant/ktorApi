package com.nameisjayant.utils

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*


suspend fun <T> PipelineContext<Unit, ApplicationCall>.response(
    response: ApiResponse<T>?
) {
    call.respond(response ?: ApiResponse())
}
