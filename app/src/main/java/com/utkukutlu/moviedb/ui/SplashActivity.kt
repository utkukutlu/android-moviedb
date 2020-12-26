package com.utkukutlu.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.utkukutlu.moviedb.AppVariables
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.model.Status
import com.utkukutlu.moviedb.util.toArrayList
import com.utkukutlu.moviedb.viewmodel.SplashViewModel
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setObservers()
        viewModel.getGenres()
    }

    private fun setObservers() {
        viewModel.genresResponse.observe(this, {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    AppVariables.genres = it.data?.genres?.toArrayList() ?: arrayListOf()
                    ListActivity.newInstance(this)
                }
                Status.ERROR -> {
                    Toasty.error(this, it.message ?: "").show()
                    ListActivity.newInstance(this)
                }
            }
        })
    }
}