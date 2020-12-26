package com.utkukutlu.moviedb.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.adapter.CastAdapter
import com.utkukutlu.moviedb.adapter.CrewAdapter
import com.utkukutlu.moviedb.databinding.ActivityDetailBinding
import com.utkukutlu.moviedb.model.Status
import com.utkukutlu.moviedb.model.data.TvShow
import com.utkukutlu.moviedb.viewmodel.DetailViewModel
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private val castAdapter = CastAdapter(arrayListOf())
    private val crewAdapter = CrewAdapter(arrayListOf())
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setViews()
        setObservers()

        intent.getParcelableExtra<TvShow>("tvShow")?.let {
            supportActionBar?.title = it.name
            viewModel.tvShow.postValue(it)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun setViews() {
        binding.recyclerViewCrew.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = crewAdapter
        }
        binding.recyclerViewCast.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@DetailActivity, RecyclerView.HORIZONTAL, false)
            adapter = castAdapter
        }
    }

    private fun setObservers() {
        viewModel.tvShow.observe(this, {
            viewModel.getCredits()
        })
        viewModel.creditsResponse.observe(this, {
            when (it.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    crewAdapter.replaceData(it.data?.crew ?: arrayListOf())
                    castAdapter.replaceData(it.data?.cast ?: arrayListOf())
                }
                Status.ERROR -> {
                    Toasty.error(this, it.message ?: "").show()
                }
            }
        })
    }


    companion object {
        fun newInstance(context: Context, tvShow: TvShow) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("tvShow", tvShow)
            context.startActivity(intent)
        }
    }

}