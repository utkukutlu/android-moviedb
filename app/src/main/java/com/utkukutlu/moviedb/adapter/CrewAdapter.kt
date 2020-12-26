package com.utkukutlu.moviedb.adapter

import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.utkukutlu.moviedb.R
import com.utkukutlu.moviedb.model.data.Crew
import com.utkukutlu.moviedb.util.loadUrl
import com.utkukutlu.moviedb.util.screenWidth

class CrewAdapter(private val items: ArrayList<Crew>) :
    BaseQuickAdapter<Crew, BaseViewHolder>(R.layout.item_crew, items) {


    override fun convert(holder: BaseViewHolder, item: Crew) {

        val itemWidth =
            if (items.size != 3) (3 + (3 / 8.0)) else 3.toDouble()
        val width = (screenWidth / itemWidth).toInt()

        val layoutContainer = holder.getView<FrameLayout>(R.id.layout_container)

        val lp = layoutContainer.layoutParams
        lp.width = width
        lp.height = RecyclerView.LayoutParams.WRAP_CONTENT

        holder.itemView.layoutParams = lp

        holder.setText(R.id.text_name, item.name)
        holder.setText(R.id.text_job, item.job)
        holder.getView<ImageView>(R.id.image_profile)
            .loadUrl(item.fullProfilePath())

    }

}