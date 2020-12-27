package com.utkukutlu.moviedb.model.datasourcefactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.utkukutlu.moviedb.model.data.TvShow
import com.utkukutlu.moviedb.model.source.TvShowPagingSource
import com.utkukutlu.moviedb.repository.TvRepository
import io.reactivex.disposables.CompositeDisposable


class TvShowDataSourceFactory(private val repository: TvRepository,private val compositeDisposable: CompositeDisposable) :
    DataSource.Factory<Int, TvShow>() {

    val dataSourceLiveData = MutableLiveData<TvShowPagingSource>()

    override fun create(): DataSource<Int, TvShow> {
        val dataSource = TvShowPagingSource(repository,compositeDisposable)
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

}