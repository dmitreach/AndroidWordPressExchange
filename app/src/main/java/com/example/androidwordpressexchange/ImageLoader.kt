package com.example.androidwordpressexchange

import android.widget.ImageView

interface ImageLoader {
    fun loadImage (imageUrl: String, imageView: ImageView)
}