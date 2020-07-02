package com.algro.resume.view.detail

import `in`.mayanknagwanshi.imagepicker.ImageSelectActivity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.algro.resume.R
import com.algro.resume.helper.activityresult.startForResult
import com.algro.resume.helper.bitmap.BitmapUtil
import com.algro.resume.helper.fragment.AbstractFragment
import com.algro.resume.helper.prompt.Prompt
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.detail.*
import kotlinx.coroutines.launch

class DetailFragment : AbstractFragment(R.layout.detail), Prompt.OnSubmit {

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

        detail_addtext.setOnClickListener {
            Prompt.show(this)
        }
    }

    suspend fun addLogo(){
        val intent = Intent(context, ImageSelectActivity::class.java)
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, false)
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true)
        startForResult(intent){
            val filepath = it.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH)!!
            setForegroundImage(filepath)
        }
    }

    override fun onSubmit(text: String) {
        val combined = BitmapUtil.appendText((detail_meme.drawable as BitmapDrawable).bitmap, text)
        setAppendedImage(combined)
    }

    private fun setForegroundImage(filepath : String){
        Glide.with(this).asBitmap()
            .load(filepath)
            .into(foregroundImageTarget)
    }

    private fun setAppendedImage(bitmap : Bitmap){
        Glide.with(this)
            .setDefaultRequestOptions(requestOptions)
            .load(bitmap)
            .into(detail_meme)
    }

    private val foregroundImageTarget = object : CustomTarget<Bitmap>(){
        override fun onLoadCleared(placeholder: Drawable?) {
        }

        override fun onResourceReady(
            resource: Bitmap,
            transition: Transition<in Bitmap>?
        ) {
            val combined = BitmapUtil.appendImage((detail_meme.drawable as BitmapDrawable).bitmap, resource)
            setAppendedImage(combined)
        }
    }

    companion object {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.placeholder_big)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .fitCenter()
    }

}