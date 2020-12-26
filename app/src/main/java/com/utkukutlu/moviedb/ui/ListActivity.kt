package com.utkukutlu.moviedb.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.adapter.TvShowAdapter
import com.utkukutlu.moviedb.databinding.ActivityListBinding
import com.utkukutlu.moviedb.model.Resource
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
            adapter = this@ListActivity.tvShowAdapter
        }
    }

    private fun setListeners() {
        tvShowAdapter.setOnLoadMoreListener({
            viewModel.page++
            viewModel.getPopularTvShows()
        }, binding.recyclerView)
    }

    private fun setObservers() {
        viewModel.listResponse.observe(this, {
            when (it) {
                is Resource.Loading -> {
                    //sadece ilk sayfa yüklenirken gösterilmesi için
                    if (viewModel.page == 1) {
                        binding.layoutProgress.visible()
                    }
                }
                is Resource.Success -> {
                    binding.layoutProgress.gone()
                    tvShowAdapter.addData(it.data.results)
                    tvShowAdapter.loadMoreComplete()
                    if (it.data.totalPages == viewModel.page) {
                        tvShowAdapter.loadMoreEnd()
                    }
                }
                is Resource.Error -> {
                    binding.layoutProgress.gone()
                    Toasty.error(this, it.message).show()
                }
            }
        })
    }
}