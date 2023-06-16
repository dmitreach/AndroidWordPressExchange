package com.example.androidwordpressexchange

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader(private val context: AllArticleFragment): ImageLoader{
    override fun loadImage(imageUrl: String, imageView: ImageView) {
        Glide.with (context)
            .load(imageUrl)
            //.centerCrop()
            .into(imageView)
    }
}