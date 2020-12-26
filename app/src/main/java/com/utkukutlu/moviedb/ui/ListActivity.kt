package com.utkukutlu.moviedb.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.utkukutlu.moviedb.AppVariables
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.adapter.TvShowAdapter
import com.utkukutlu.moviedb.databinding.ActivityListBinding
import com.utkukutlu.moviedb.model.Status
import com.utkukutlu.moviedb.util.gone
import com.utkukutlu.moviedb.util.visible
import com.utkukutlu.moviedb.viewmodel.ListViewModel
import es.dmoral.toasty.Toasty
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val tvShowAdapter = TvShowAdapter(arrayListOf())
    private val viewModel: ListViewModel by viewModel()
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.lifecycleOwner = this
        setViews()
        setListeners()
        setObservers()
        viewModel.getPopularTvShows()
    }

    private fun setViews() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@ListActivity, 3)
            adapter = tvShowAdapter
        }
    }

    private fun setListeners() {
        tvShowAdapter.setOnLoadMoreListener({
            viewModel.page++
            viewModel.getPopularTvShows()
        }, binding.recyclerView)
        tvShowAdapter.setOnItemClickListener { _, _, position ->
            viewModel.listResponse.value?.data
            viewModel.tvShows.value?.get(position)?.let {
                DetailActivity.newInstance(this, it)
            }
        }
    }

    private fun setObservers() {
        viewModel.listResponse.observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    //sadece ilk sayfa yüklenirken gösterilmesi için
                    if (viewModel.page == 1) {
                        binding.layoutProgress.visible()
                    }
                }
                Status.SUCCESS -> {
                    binding.layoutProgress.gone()
                    val list = it.data?.results ?: arrayListOf()
                    list.map { mTvShow ->
                        val genres = arrayListOf<String>()
                        mTvShow.genreIds.forEach { mGenreId ->
                            AppVariables.genres.find { it.id == mGenreId }?.let { mGenre ->
                                genres.add(mGenre.name)
                            }
                        }
                        mTvShow.genres = genres
                    }
                    viewModel.tvShows.value?.addAll(list)
                    tvShowAdapter.addData(list)
                    tvShowAdapter.loadMoreComplete()
                    if (it.data?.totalPages == viewModel.page) {
                        tvShowAdapter.loadMoreEnd()
                    }
                }
                Status.ERROR -> {
                    binding.layoutProgress.gone()
                    Toasty.error(this, it.message ?: "").show()
                }
            }
        })
    }


    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, ListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            context.startActivity(intent)
        }
    }

}