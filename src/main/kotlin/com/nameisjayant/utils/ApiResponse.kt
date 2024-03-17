package com.nameisjayant.utils

import io.ktor.http.*


data class ApiResponse<out T>(
    val success: Boolean? = null,
    val statusCode: HttpStatusCode? = null,
    val message: String? = null,
    val data:List<T>?=null
)