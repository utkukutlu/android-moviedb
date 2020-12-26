package com.utkukutlu.moviedb.di

import com.utkukutlu.moviedb.repository.GenreRepository
import com.utkukutlu.moviedb.repository.TvRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { TvRepository() }
    single { GenreRepository() }
}