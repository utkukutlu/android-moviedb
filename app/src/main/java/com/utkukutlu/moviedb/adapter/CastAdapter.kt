package com.utkukutlu.moviedb.adapter

import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.model.data.Cast
import com.utkukutlu.moviedb.util.loadUrl
import com.utkukutlu.moviedb.util.screenWidth

class CastAdapter(private val items: ArrayList<Cast>) :
    BaseQuickAdapter<Cast, BaseViewHolder>(R.layout.item_cast, items) {


    override fun convert(holder: BaseViewHolder, item: Cast) {

        val itemWidth =
            if (items.size != 2) (2 + (2 / 8.0)) else 2.toDouble()
        val width = (screenWidth / itemWidth).toInt()

        val layoutContainer = holder.getView<FrameLayout>(R.id.layout_container)

        val lp = layoutContainer.layoutParams
        lp.width = width
        lp.height = RecyclerView.LayoutParams.WRAP_CONTENT

        holder.itemView.layoutParams = lp

        holder.setText(R.id.text_name, "${item.name}\n(${item.character})")
        holder.getView<ImageView>(R.id.image_profile)
            .loadUrl(item.fullProfilePath())

    }

}