package com.utkukutlu.moviedb.di

import com.utkukutlu.moviedb.viewmodel.DetailViewModel
import com.utkukutlu.moviedb.viewmodel.ListViewModel
import com.utkukutlu.moviedb.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ListViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SplashViewModel(get()) }
}