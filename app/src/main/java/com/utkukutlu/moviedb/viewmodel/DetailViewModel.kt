package com.utkukutlu.moviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import com.utkukutlu.moviedb.model.Resource
import com.utkukutlu.moviedb.model.data.TvShow
import com.utkukutlu.moviedb.model.response.CreditsResponse
import com.utkukutlu.moviedb.repository.TvRepository
import com.utkukutlu.moviedb.util.addToCompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(private val repository: TvRepository) : BaseViewModel() {
    val tvShow = MutableLiveData<TvShow>()


    val creditsResponse = MutableLiveData<Resource<CreditsResponse>>()

    fun getCredits() {
        tvShow.value?.id?.let {
            repository.getCredits(it)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    creditsResponse.postValue(it)
                }
                .addToCompositeDisposable(compositeDisposable)
        }

    }


}