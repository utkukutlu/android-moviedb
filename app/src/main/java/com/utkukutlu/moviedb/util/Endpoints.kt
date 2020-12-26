package com.utkukutlu.moviedb.util

import com.utkukutlu.moviedb.model.response.CreditsResponse
import com.utkukutlu.moviedb.model.response.GenresResponse
import com.utkukutlu.moviedb.model.response.PopularTvShowsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface Endpoints {

    @GET("tv/popular")
    fun getPopularTvShows(@QueryMap params: HashMap<String, Any>): Single<PopularTvShowsResponse>

    @GET("genre/tv/list")
    fun getGenres(@QueryMap params: HashMap<String, Any>): Single<GenresResponse>

    @GET("tv/{tv_id}/credits")
    fun getCredits(
        @Path("tv_id") tvId: Int,
        @QueryMap params: HashMap<String, Any>
    ): Single<CreditsResponse>

}