package com.utkukutlu.moviedb.repository

import com.utkukutlu.moviedb.Config
import com.utkukutlu.moviedb.model.Resource
import com.utkukutlu.moviedb.model.response.PopularTvShowsResponse
import com.utkukutlu.moviedb.util.ApiService
import com.utkukutlu.moviedb.util.fetchResponse
import io.reactivex.Observable

class TvRepository {

    fun getPopularTvShows(page: Int = 1): Observable<Resource<PopularTvShowsResponse>> {
        val params = Config.getDefaultParams()
        params["page"] = page
        return ApiService.on.getPopularTvShows(params).fetchResponse()
    }

}