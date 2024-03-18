package com.nameisjayant.features.user.routes

import com.nameisjayant.data.repository.UserRepositoryImp
import com.nameisjayant.features.user.domain.modal.User
import com.nameisjayant.features.user.domain.validations.*
import com.nameisjayant.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*


fun Application.configureUserRoutes(
    repository: UserRepositoryImp
) {

    routing {
        post("/api/user") {
            try {
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

                // validations
                isEmailEmpty(email)
                isValidateEmail(email)
                isPasswordEmpty(password)
                isPasswordValid(password)
                checkEmailAlreadyExists(repository.getAllEmail(), email)

                val response = repository.createUser(User(email = email, password = password))
                response?.let {
                    response(
                        ApiResponse(
                            statusCode = HttpStatusCode.OK,
                            success = true,
                            message = "User Created Successfully",
                            data = listOf(User(email = it.email))
                        )
                    )
                }
            } catch (e: Exception) {
                response<User>(
                    ApiResponse(
                        statusCode = HttpStatusCode.BadRequest,
                        success = false,
                        message = e.message
                    )
                )
            }
        }
    }
}