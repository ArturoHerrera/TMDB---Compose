package com.arthur.tmdb.utils

sealed class ServiceResult<out R> {
    data class Success<out T>(val dto: T?): ServiceResult<T>()
    data class Error(val message: String): ServiceResult<Nothing>()
}

val ServiceResult<*>.succeeded
    get() = this is ServiceResult.Success && dto != null

fun <T> ServiceResult<T>.getDto(): T {
    return (this as ServiceResult.Success<T>).dto!!
}

fun ServiceResult<*>.getMessage(): String {
    return (this as ServiceResult.Error).message
}