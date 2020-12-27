package com.utkukutlu.moviedb.repository

import com.utkukutlu.moviedb.AppVariables
import com.utkukutlu.moviedb.model.Resource
import com.utkukutlu.moviedb.model.response.CreditsResponse
import com.utkukutlu.moviedb.model.response.PopularTvShowsResponse
import com.utkukutlu.moviedb.util.ApiService
import com.utkukutlu.moviedb.util.fetchResponse
import io.reactivex.Observable
import io.reactivex.Single

class TvRepository {

    fun getPopularTvShows(page: Int = 1): Single<PopularTvShowsResponse> {
        val params = AppVariables.getDefaultParams()
        params["page"] = page
        return ApiService.on.getPopularTvShows(params)
    }

//    suspend fun getPopularTvShowsCoroutines(page: Int = 1): PopularTvShowsResponse {
//        val params = AppVariables.getDefaultParams()
//        params["page"] = page
//        return ApiService.on.getPopularTvShowsCoroutines(params)
//    }

    fun getCredits(tvId: Int): Observable<Resource<CreditsResponse>> {
        val params = AppVariables.getDefaultParams()
        return ApiService.on.getCredits(tvId, params).fetchResponse()
    }

}