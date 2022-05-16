package com.arthur.tmdb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MoviePopularDto(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieDataDto>?,
    @SerializedName("total_results") val total_results: Int?,
    @SerializedName("total_pages") val total_pages: Int?,
    @SerializedName("dates") val dates: List<Dates>?
)

data class Dates (
    @SerializedName("maximum" ) var maximum : String? = null,
    @SerializedName("minimum" ) var minimum : String? = null
)