package com.nameisjayant.features.user.routes

import com.nameisjayant.data.repository.UserRepositoryImp
import com.nameisjayant.features.user.domain.modal.User
import com.nameisjayant.plugins.UserRoute
import com.nameisjayant.utils.ApiResponse
import com.nameisjayant.utils.response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureUserRoutes(
    repository: UserRepositoryImp
) {

    routing {
        post("/api/user") {
            val userData = call.receive<User>()
            val email = userData.email ?: return@post response<User>(
                ApiResponse(
                    statusCode = HttpStatusCode.BadRequest,
                    success = false,
                    message = "Missing Email Field"
                )
            )
            val password = userData.password ?: return@post response<User>(
                ApiResponse(
                    statusCode = HttpStatusCode.BadRequest,
                    success = false,
                    message = "Missing Password Field"
                )
            )
        }
    }
}