package com.nameisjayant.utils

import com.nameisjayant.data.modal.ApiResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*


suspend fun <T> PipelineContext<Unit, ApplicationCall>.response(
    response: ApiResponse<T>?
) {
    call.respond(status = response?.statusCode ?: HttpStatusCode.OK, message = response ?: ApiResponse())
}
