package com.example.qiitaclient.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.example.qiitaclient.R
import com.example.qiitaclient.model.Article
import kotlinx.android.synthetic.main.view_article.view.*

class ArticleView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_article, this)
    }

    fun setArticle(article: Article) {
        title_text_view.text = article.title
        user_name_text_view.text = article.user.name
        Glide.with(context).load(article.user.profileImageUrl).into(profile_image_view)
    }
}
