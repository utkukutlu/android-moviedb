package com.utkukutlu.moviedb.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.chad.library.adapter.base.BaseViewHolder
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.model.OnItemClickListener
import com.utkukutlu.moviedb.model.data.TvShow
import com.utkukutlu.moviedb.util.loadUrl

class TvShowAdapters(private val items: ArrayList<TvShow>) :
    PagedListAdapter<TvShow, BaseViewHolder>(notificationDiffCallback) {

    private var mSetOnItemClickListener: OnItemClickListener? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val mItem = getItem(position)
        mItem?.let { item ->
            holder.setText(R.id.text_title, item.name)
            holder.getView<ImageView>(R.id.image_cover)
                .loadUrl(item.fullPosterPath())
            holder.setText(R.id.text_rating, "${item.voteAverage}/10")
            holder.setText(R.id.text_origin_country, item.originCountry?.firstOrNull())
            holder.setText(R.id.text_first_air_date, item.firstAirDate)
            holder.itemView.setOnClickListener {
                mSetOnItemClickListener?.invoke(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_tv_show, parent, false)
        )
    }

    companion object {
        private val notificationDiffCallback = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mSetOnItemClickListener = listener
    }

}