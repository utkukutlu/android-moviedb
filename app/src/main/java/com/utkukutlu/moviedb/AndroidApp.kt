package com.utkukutlu.moviedb

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.utkukutlu.moviedb.di.repositoryModule
import com.utkukutlu.moviedb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidContext(this@AndroidApp)
            modules(repositoryModule)
            modules(viewModelModule)
        }
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        lateinit var context:Context
    }

}
