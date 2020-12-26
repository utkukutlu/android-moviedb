package com.utkukutlu.moviedb.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.model.Resource
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions().fitCenter().placeholder(R.color.grey_800).error(R.mipmap.ic_launcher)
        )
        .into(this)
}

fun Disposable.addToCompositeDisposable(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}

fun <T> Single<T>.fetchResponse(): Observable<Resource<T>> {
    return Observable.create { emitter ->
        emitter.onNext(Resource.Loading)
        subscribeOn(Schedulers.io())
            .subscribe({
                emitter.onNext(Resource.Success(it))
                emitter.onComplete()
            }, {
                emitter.onNext(Resource.Error(it.message ?: ""))
                emitter.onComplete()
            })
    }
}
