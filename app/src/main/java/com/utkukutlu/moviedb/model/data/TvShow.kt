package com.utkukutlu.moviedb.model.data

import com.squareup.moshi.Json

data class TvShow(
    val id: Int,
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    val overview: String,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "vote_average")
    val voteAverage: Double
)