package com.utkukutlu.moviedb.model.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShow(
    val id: Int,
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    val overview: String,
    @Json(name = "vote_count")
    val voteCount: Int,
    @Json(name = "vote_average")
    val voteAverage: Double,
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "first_air_date")
    val firstAirDate: String?,
    @Json(name = "origin_country")
    val originCountry: List<String>?,
    var genres: List<String>?
) : Parcelable {
    fun fullPosterPath() = "https://image.tmdb.org/t/p/w500/$posterPath"
    fun fullBackdropPath() = "https://image.tmdb.org/t/p/w500/$backdropPath"
    fun voteAverageStr() = "$voteAverage/10"
    fun genresStr(): String {
        return genres?.joinToString(", ") ?: ""
    }
}