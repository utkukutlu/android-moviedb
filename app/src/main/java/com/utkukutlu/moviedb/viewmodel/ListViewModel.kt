package com.utkukutlu.moviedb.viewmodel

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.utkukutlu.moviedb.model.datasourcefactory.TvShowDataSourceFactory
import com.utkukutlu.moviedb.repository.TvRepository

class ListViewModel(
    private val repository: TvRepository
) : BaseViewModel() {

    private var dataSourceFactory: TvShowDataSourceFactory =
        TvShowDataSourceFactory(repository, compositeDisposable)
    private val config = PagedList.Config.Builder()
        .setPrefetchDistance(5)
        .build()

    var tvShowsPagedList = LivePagedListBuilder(dataSourceFactory, config).build()


}