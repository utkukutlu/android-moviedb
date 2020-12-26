package com.utkukutlu.moviedb.model.response

import com.squareup.moshi.Json
import com.utkukutlu.moviedb.model.data.TvShow

data class PopularTvShowsResponse(
    val page: Int,
    val results: List<TvShow> = listOf(),
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int
)