package com.algro.resume.helper.bitmap

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix

object BitmapUtil {

    fun combine(background : Bitmap, foreground : Bitmap) : Bitmap{
        val overlay = Bitmap.createBitmap(background.width, background.height, background.config)
        val canvas = Canvas(overlay)
        canvas.drawBitmap(foreground, Matrix(), null)
        canvas.drawBitmap(background,0f,0f, null)
        return overlay
    }

}