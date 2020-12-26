package com.utkukutlu.moviedb.model.response

import com.utkukutlu.moviedb.model.data.Genre

data class GenresResponse(
    val genres: List<Genre>
)