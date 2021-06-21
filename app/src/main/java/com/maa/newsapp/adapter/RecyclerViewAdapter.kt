package com.maa.newsapp.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.ShapeAppearanceModel
import com.maa.newsapp.databinding.NewsItemBinding
import com.maa.newsapp.model.News
import com.squareup.picasso.Picasso


class RecyclerViewAdapter(val itemlist: ArrayList<News>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.newsFrame.shapeAppearanceModel= ShapeAppearanceModel().toBuilder().setAllCorners(
            CornerFamily.ROUNDED,
            20F
        ).build()
        holder.binding.newsImage.shapeAppearanceModel= ShapeAppearanceModel().toBuilder().setTopLeftCorner(
            CornerFamily.ROUNDED,
            20F
        ).setTopRightCorner(
                CornerFamily.ROUNDED,
        20F
        ).build()
        holder.binding.newsTitle.text = itemlist[position].title
        holder.binding.newsDescription.text = itemlist[position].description
        holder.binding.newsDate.text = itemlist[position].publishedAt.replace("T", " ").replace(
            "Z",
            " "
        )
        Picasso.get().load(itemlist[position].urlToImage).into(holder.binding.newsImage)

        holder.itemView.setOnClickListener{
            val defaultBrowser = Intent.makeMainSelectorActivity(Intent.ACTION_MAIN, Intent.CATEGORY_APP_BROWSER)
            defaultBrowser.data = Uri.parse(itemlist[position].url)
            holder.itemView.context.startActivity(defaultBrowser)
        }
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }

    inner class ViewHolder(val binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}