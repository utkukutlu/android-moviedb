package com.utkukutlu.moviedb.model.data

import com.squareup.moshi.Json

data class Cast(
    val id: Int,
    val name: String,
    @Json(name = "profile_path")
    val profilePath: String,
    val character: String
) {
    fun fullProfilePath() = if (profilePath.isNullOrEmpty()) {
        null
    } else {
        "https://image.tmdb.org/t/p/w500/$profilePath"
    }
}