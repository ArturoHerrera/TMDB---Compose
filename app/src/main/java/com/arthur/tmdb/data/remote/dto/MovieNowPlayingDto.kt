package com.arthur.tmdb.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieNowPlayingDto(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieDataDto>?,
    @SerializedName("total_results") val total_results: Int?,
    @SerializedName("total_pages") val total_pages: Int?
)

data class MovieDataDto(
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
    @SerializedName("video") var video: Boolean? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null
)