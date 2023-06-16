package com.example.androidwordpressexchange.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwordpressexchange.ImageLoader
import com.example.androidwordpressexchange.R

class ArticleAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
) : RecyclerView.Adapter<ArticleViewHolder>() {

    private val articlesData = mutableListOf <ArticleUiModel> ()

    fun setData (articleData: List <ArticleUiModel>) {
        this.articlesData.clear()
        this.articlesData.addAll(articleData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = layoutInflater.inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view, imageLoader, object : ArticleViewHolder.OnClickListener {
                override fun onClick(articleData: ArticleUiModel) = onClickListener.onItemClick(articleData)
            })
    }

    override fun getItemCount() = articlesData.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(articlesData[position])

    }

    interface OnClickListener {
        fun onItemClick(articleData: ArticleUiModel)
    }

}