package com.utkukutlu.moviedb.model.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.utkukutlu.moviedb.model.data.TvShow
import com.utkukutlu.moviedb.repository.TvRepository
import com.utkukutlu.moviedb.util.addToCompositeDisposable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class TvShowPagingSource(
    private val repository: TvRepository,
    private val compositeDisposable: CompositeDisposable
) :
    PageKeyedDataSource<Int, TvShow>() {

    private val TAG = "TvShowPagingSource"

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShow>
    ) {
        repository.getPopularTvShows(1)
            .subscribeOn(Schedulers.io())
            .subscribe({
                callback.onResult(it.results, null, 2)
            }, {
                Log.e(TAG, it.localizedMessage ?: "")
            })
            .addToCompositeDisposable(compositeDisposable)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
        repository.getPopularTvShows(params.key)
            .subscribeOn(Schedulers.io())
            .subscribe({
                if (it.page < it.totalPages) {
                    callback.onResult(it.results, (params.key + 1))
                }
            }, {
                Log.e(TAG, it.localizedMessage ?: "")
            })
            .addToCompositeDisposable(compositeDisposable)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShow>) {
    }

}