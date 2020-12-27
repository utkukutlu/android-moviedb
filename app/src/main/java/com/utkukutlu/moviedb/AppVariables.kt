package com.utkukutlu.moviedb

import com.utkukutlu.moviedb.model.data.Genre

object AppVariables {

    var genres:ArrayList<Genre> = arrayListOf()

    fun getDefaultParams():HashMap<String,Any> {
        return hashMapOf<String,Any>().apply {
            put("api_key",BuildConfig.API_KEY)
            put("language","tr")
        }
    }
}