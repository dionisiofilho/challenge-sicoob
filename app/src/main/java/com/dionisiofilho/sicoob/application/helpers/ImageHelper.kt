package com.dionisiofilho.sicoob.application.helpers

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dionisiofilho.sicoob.BuildConfig
import com.dionisiofilho.sicoob.enums.ImageSize
import com.dionisiofilho.sicoob.extensions.getAppContext
import org.jetbrains.annotations.NotNull

class ImageHelper {

    companion object {

        fun getImageFromImageView(@NotNull urlImage: String, @NotNull imageView: ImageView) {
            Glide.with(getAppContext()).load(urlImage).into(imageView)
        }

        fun getImageFromMovie(@NotNull urlImage: String, @NotNull imageView: ImageView, imageSize: ImageSize = ImageSize.W185) {
            Glide.with(getAppContext()).load(BuildConfig.BaseURLImage + imageSize.getSize() + urlImage).into(imageView)
        }

        fun clearImage(@NotNull imageView: ImageView) {
            Glide.with(getAppContext()).clear(imageView)

        }
    }
}