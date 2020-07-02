package com.algro.resume.view.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.algro.resume.R
import com.algro.resume.helper.fragment.AbstractFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.detail.*

class DetailFragment : AbstractFragment(R.layout.detail) {

    private val arg by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .setDefaultRequestOptions(requestOptions)
            .load(arg.meme.imageUrl)
            .into(detail_meme)
    }

    companion object {

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder_big)
            .fitCenter()
            .diskCacheStrategy(DiskCacheStrategy.DATA)

    }


}