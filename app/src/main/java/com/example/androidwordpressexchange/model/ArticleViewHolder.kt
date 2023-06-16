package com.example.androidwordpressexchange.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwordpressexchange.ImageLoader
import com.example.androidwordpressexchange.R

class ArticleViewHolder (
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
): RecyclerView.ViewHolder(containerView) {

    private val titleArticleView: TextView by lazy { containerView.findViewById(R.id.titleArticle) }
    private val shortTextArticleView: TextView by lazy { containerView.findViewById(R.id.shortTextArticle) }
    private val imageArticleView: ImageView by lazy { containerView.findViewById(R.id.imageArticle) }
    // this all was at main activitity , now this all at ArticleViewHolder


    fun bindData (articleData: ArticleUiModel) {
        containerView.setOnClickListener {onClickListener.onClick(articleData)}
        imageLoader.loadImage(articleData.imageUrl, imageArticleView)
        titleArticleView.text = articleData.title
        shortTextArticleView.text = articleData.shortText
    }

    interface OnClickListener {
        fun onClick(articleData: ArticleUiModel)
    }

}