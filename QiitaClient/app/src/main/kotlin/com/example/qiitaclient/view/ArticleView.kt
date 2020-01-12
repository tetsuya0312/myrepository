package com.example.qiitaclient.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import com.example.qiitaclient.R
import com.example.qiitaclient.databinding.ViewArticleBinding
import com.example.qiitaclient.model.Article

@BindingMethods(
    BindingMethod( // 内側のアノテーションには@が不要
        type = Article::class,
        attribute = "bind:article",
        method = "setArticle"
    )
)
class ArticleView : FrameLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    private val binding: ViewArticleBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context), R.layout.view_article,
        this, true
    )

    fun setArticle(article: Article) {
        binding.article = article
    }
}
