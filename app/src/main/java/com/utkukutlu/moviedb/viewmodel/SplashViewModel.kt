package com.utkukutlu.moviedb.viewmodel

import androidx.lifecycle.MutableLiveData
import com.utkukutlu.moviedb.model.Resource
import com.utkukutlu.moviedb.model.response.GenresResponse
import com.utkukutlu.moviedb.repository.GenreRepository
import com.utkukutlu.moviedb.util.addToCompositeDisposable
import io.reactivex.schedulers.Schedulers

class SplashViewModel(private val genreRepository: GenreRepository) : BaseViewModel() {

    val genresResponse = MutableLiveData<Resource<GenresResponse>>()

    fun getGenres() {
        genreRepository.getGenres()
            .subscribeOn(Schedulers.io())
            .subscribe {
                genresResponse.postValue(it)
            }
            .addToCompositeDisposable(compositeDisposable)
    }

}