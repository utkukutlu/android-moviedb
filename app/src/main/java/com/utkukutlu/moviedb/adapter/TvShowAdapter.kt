package com.utkukutlu.moviedb.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.model.data.TvShow
import com.utkukutlu.moviedb.util.loadUrl

class TvShowAdapter(private val items: ArrayList<TvShow>) :
    BaseQuickAdapter<TvShow, BaseViewHolder>(R.layout.item_tv_show, items) {

    override fun convert(holder: BaseViewHolder, item: TvShow) {
        holder.setText(R.id.text_title, item.name)
        holder.getView<ImageView>(R.id.image_cover)
            .loadUrl(item.fullPosterPath())
        holder.setText(R.id.text_rating, "${item.voteAverage}/10")
        holder.setText(R.id.text_origin_country, item.originCountry?.firstOrNull())
        holder.setText(R.id.text_first_air_date, item.firstAirDate)
    }

}