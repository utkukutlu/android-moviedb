package com.utkukutlu.moviedb

object Config {
    fun getDefaultParams():HashMap<String,Any> {
        return hashMapOf<String,Any>().apply {
            put("api_key",BuildConfig.API_KEY)
            put("language","tr")
        }
    }
}