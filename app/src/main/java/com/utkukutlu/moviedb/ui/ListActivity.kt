package com.utkukutlu.moviedb.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.adapter.TvShowAdapters
import com.utkukutlu.moviedb.databinding.ActivityListBinding
import com.utkukutlu.moviedb.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListActivity : AppCompatActivity() {

    private val tvShowAdapter = TvShowAdapters(arrayListOf())
    private val viewModel: ListViewModel by viewModel()
    private lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.lifecycleOwner = this
        setViews()
        setListeners()
        setObservers()
    }

    private fun setViews() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(this@ListActivity, 3)
            adapter = tvShowAdapter
        }
    }

    private fun setListeners() {
        tvShowAdapter.setOnItemClickListener { position ->
            viewModel.tvShowsPagedList.value?.getOrNull(position)?.let {
                DetailActivity.newInstance(this, it)
            }
        }
    }

    private fun setObservers() {
        viewModel.tvShowsPagedList.observe(this, {
            tvShowAdapter.submitList(it)
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