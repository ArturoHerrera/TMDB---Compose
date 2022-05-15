package com.arthur.tmdb.utils

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.HashMap

object HttpUtils {

    const val OK = 200
    const val CREATED = 201
    const val NO_CONTENT = 204
    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val CONFLICT = 409
    const val INTERNAL_SERVER_ERROR = 500

    private val httpCodesMap: Map<Int, String> = object : HashMap<Int, String>() {
        init {
            put(OK, "OK")
            put(CREATED, "Created")
            put(NO_CONTENT, "Created")
            put(BAD_REQUEST, "Bad Request")
            put(UNAUTHORIZED, "Unauthorized")
            put(FORBIDDEN, "Forbidden")
            put(NOT_FOUND, "Not Found")
            put(CONFLICT, "Conflict")
            put(INTERNAL_SERVER_ERROR, "Internal Server Error")
        }
    }

    fun getErrorNameFromHttpCode(httpErrorCode: Int): String? {
        if (httpCodesMap.containsKey(httpErrorCode)) {
            return httpCodesMap[httpErrorCode]
        }
        return httpErrorCode.toString()
    }

}