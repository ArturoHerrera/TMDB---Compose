package com.arthur.tmdb.utils

object ApiUtils {

    fun getStatusCodeErrorMessage(statusCode: Int): String {
        return String.format("Error (%d): %s", statusCode, HttpUtils.getErrorNameFromHttpCode(statusCode))
    }
}