package com.nameisjayant.utils

sealed class ApiState <out T>{
    data class Success<T>(val data:T) : ApiState<T>()
    data class Error(val message:String) : ApiState<Nothing>()
}