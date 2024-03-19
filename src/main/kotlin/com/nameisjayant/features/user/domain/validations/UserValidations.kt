package com.nameisjayant.features.user.domain.validations

import com.nameisjayant.features.user.domain.modal.User
import com.nameisjayant.data.modal.ApiResponse
import com.nameisjayant.utils.response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.util.pipeline.*
import java.util.regex.Pattern


private const val EMAIL_PATTERN =
    "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"

suspend fun PipelineContext<Unit, ApplicationCall>.isValidateEmail(email: String): Unit = run {
    val matches = Pattern.compile(EMAIL_PATTERN).matcher(email).matches()
    if (!matches) {
        response<User>(
            ApiResponse(
                statusCode = HttpStatusCode.BadRequest,
                success = false,
                message = "Email is not valid!"
            )
        )
        throw IllegalArgumentException("Email is not valid")
    }

}

suspend fun PipelineContext<Unit, ApplicationCall>.isEmailEmpty(email: String) {
    if (email.isEmpty()) {
        response<User>(
            ApiResponse(
                statusCode = HttpStatusCode.BadRequest,
                success = false,
                message = "Email cannot be empty!"
            )
        )
        throw IllegalArgumentException("Email cannot be empty!")
    }
}

suspend fun PipelineContext<Unit, ApplicationCall>.isPasswordValid(password: String) {
    if (password.length < 6) {
        response<User>(
            ApiResponse(
                statusCode = HttpStatusCode.BadRequest,
                success = false,
                message = "Password should be 6 characters long..!"
            )
        )
        throw IllegalArgumentException("Password should be 6 characters long..")
    }

}

suspend fun PipelineContext<Unit, ApplicationCall>.isPasswordEmpty(password: String) {
    if (password.isEmpty()) {
        response<User>(
            ApiResponse(
                statusCode = HttpStatusCode.BadRequest,
                success = false,
                message = "Password cannot be Empty..!"
            )
        )
        throw IllegalArgumentException("Password cannot be Empty..!")
    }

}

suspend fun PipelineContext<Unit, ApplicationCall>.checkEmailAlreadyExists(emails: List<String>?, email: String) {
    emails?.let {
        if (it.contains(email)) {
            response<User>(
                ApiResponse(
                    statusCode = HttpStatusCode.BadRequest,
                    success = false,
                    message = "Email already Exists..!"
                )
            )
        }
        throw IllegalArgumentException("Email already Exists..!")
    }
}