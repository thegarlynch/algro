package com.algro.resume.view.front

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.algro.resume.R
import com.algro.resume.domain.model.Meme
import com.algro.resume.helper.recyclerview.DefaultItemCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MemeFrontAdapter : ListAdapter<Meme, MemeFrontAdapter.ViewHolder>(DefaultItemCallback()) {

    class ViewHolder(parent : ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.meme_item, parent, false)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder.itemView as ImageView).apply {
            /**
             *  TODO : Don't use context since it is single activity application
             *  use [RequestManager] instead
             */
            Glide.with(context)
                .setDefaultRequestOptions(requestOptions)
                .load(getItem(position).imageUrl)
                .into(this)
        }
    }

    companion object {

        internal val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)

    }
}