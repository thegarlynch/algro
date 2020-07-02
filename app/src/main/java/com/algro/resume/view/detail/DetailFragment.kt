package com.algro.resume.view.detail

import `in`.mayanknagwanshi.imagepicker.ImageSelectActivity
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.algro.resume.R
import com.algro.resume.helper.bitmap.BitmapUtil
import com.algro.resume.helper.fragment.AbstractFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.github.florent37.inlineactivityresult.kotlin.InlineActivityResultException
import com.github.florent37.inlineactivityresult.kotlin.coroutines.startForResult
import kotlinx.android.synthetic.main.detail.*
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DetailFragment : AbstractFragment(R.layout.detail) {

    private val arg by navArgs<DetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext())
            .setDefaultRequestOptions(requestOptions)
            .asBitmap()
            .load(arg.meme.imageUrl)
            .into(detail_meme)

        detail_addlogo.setOnClickListener {
            lifecycleScope.launch {
                addLogo()
            }
        }
    }

    suspend fun addLogo(){
        try {
            val intent = Intent(context, ImageSelectActivity::class.java)
            intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false)
            intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true)
            val result = startForResult(intent)
            if(result.resultCode == Activity.RESULT_OK){
                val filepath = result.data!!.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH)!!
                Glide.with(this).asBitmap()
                    .load(filepath)
                    .into(object : CustomTarget<Bitmap>(){

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }

                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            val combined = BitmapUtil.combine((detail_meme.drawable as BitmapDrawable).bitmap, resource)
                            Glide.with(this@DetailFragment)
                                .setDefaultRequestOptions(requestOptions)
                                .load(combined)
                                .into(detail_meme)
                        }
                    })
            }
        }catch (ex : InlineActivityResultException){ }
    }

    companion object {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder_big)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .fitCenter()
    }


}