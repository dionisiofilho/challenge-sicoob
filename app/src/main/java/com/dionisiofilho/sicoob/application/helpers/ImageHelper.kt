package com.dionisiofilho.sicoob.application.helpers

import android.graphics.BitmapFactory
import android.widget.ImageView
import org.jetbrains.annotations.NotNull
import java.net.URL

class ImageHelper {

    companion object {

        fun getImageFromImageView(@NotNull urlImage: String, @NotNull imageView: ImageView) {

            val url = URL(urlImage)

            val httpURLConnection = url.openStream().apply {
                imageView.setImageBitmap(BitmapFactory.decodeStream(this))
            }
        }

    }
}