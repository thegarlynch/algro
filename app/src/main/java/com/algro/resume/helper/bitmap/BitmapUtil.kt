package com.algro.resume.helper.bitmap

import android.graphics.*
import com.algro.resume.helper.misc.dp
import com.algro.resume.helper.misc.dpF
import com.algro.resume.helper.misc.sp

object BitmapUtil {

    fun appendImage(background : Bitmap, foreground : Bitmap) : Bitmap{
        val overlay = Bitmap.createBitmap(background.width, background.height, background.config)
        val canvas = Canvas(overlay)
        canvas.drawBitmap(background, Matrix(), null)
        canvas.drawBitmap(foreground,0f, 0f, null)
        return overlay
    }

    fun appendText(background: Bitmap, text : String) : Bitmap {
        val overlay = Bitmap.createBitmap(background.width, background.height, background.config)
        val canvas = Canvas(overlay)
        val paint = Paint().apply {
            color = Color.BLACK
            isAntiAlias = true
            textSize = 20f
            textAlign = Paint.Align.LEFT
        }
        canvas.drawBitmap(background, Matrix(), paint)
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)
        canvas.drawText(text, 0, text.length - 1, 0f, 50f,paint)
        return overlay
    }

}