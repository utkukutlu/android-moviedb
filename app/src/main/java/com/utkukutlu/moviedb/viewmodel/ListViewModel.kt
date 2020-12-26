package com.utkukutlu.moviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import com.utkukutlu.moviedb.model.Resource
import com.utkukutlu.moviedb.model.data.TvShow
import com.utkukutlu.moviedb.model.response.PopularTvShowsResponse
import com.utkukutlu.moviedb.repository.TvRepository
import com.utkukutlu.moviedb.util.addToCompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListViewModel(
    private val repository: TvRepository
) : BaseViewModel() {

    var page = 1

    val tvShows = MutableLiveData<ArrayList<TvShow>>().apply {
        value = arrayListOf()
    }

    val listResponse = MutableLiveData<Resource<PopularTvShowsResponse>>()

    fun getPopularTvShows() {
        repository.getPopularTvShows(page)
            .subscribeOn(Schedulers.io())
            .subscribe {
                listResponse.postValue(it)
            }
            .addToCompositeDisposable(compositeDisposable)
    }



}