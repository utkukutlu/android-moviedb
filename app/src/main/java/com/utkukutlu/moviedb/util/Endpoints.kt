package com.utkukutlu.moviedb.util

import com.utkukutlu.moviedb.model.response.PopularTvShowsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface Endpoints {

    @GET("tv/popular")
    fun getPopularTvShows(@QueryMap params:HashMap<String,Any>):Single<PopularTvShowsResponse>

}