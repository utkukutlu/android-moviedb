package com.utkukutlu.moviedb.repository

import com.utkukutlu.moviedb.AppVariables
import com.utkukutlu.moviedb.model.Resource
import com.utkukutlu.moviedb.model.response.GenresResponse
import com.utkukutlu.moviedb.util.ApiService
import com.utkukutlu.moviedb.util.fetchResponse
import io.reactivex.Observable

class GenreRepository {

    fun getGenres(): Observable<Resource<GenresResponse>> {
        val params = AppVariables.getDefaultParams()
        return ApiService.on.getGenres(params).fetchResponse()
    }

}