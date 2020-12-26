package com.utkukutlu.moviedb.model.response

import com.utkukutlu.moviedb.model.data.Cast
import com.utkukutlu.moviedb.model.data.Crew

data class CreditsResponse(
    val cast:List<Cast>,
    val crew:List<Crew>
)