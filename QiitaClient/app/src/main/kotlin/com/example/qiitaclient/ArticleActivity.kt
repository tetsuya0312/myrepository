package com.example.qiitaclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.qiitaclient.model.Article
import com.example.qiitaclient.model.User
import com.example.qiitaclient.view.ArticleView


class ArticleActivity : AppCompatActivity() {

    companion object {
        private const val ARTICLE_EXTRA: String = "article"

        fun intent(context: Context, article: Article): Intent =
            Intent(context, ArticleActivity::class.java).putExtra(ARTICLE_EXTRA, article)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val articleView = findViewById<ArticleView>(R.id.article_view)
        val webView = findViewById<WebView>(R.id.web_view)

        val article: Article = getParcelableExtra()
        articleView.setArticle(article)
        webView.loadUrl(article.url)
    }

    private fun getParcelableExtra(): Article =
        // getParcelableExtra が Nullable なのでエルビス演算子で回避
        intent.getParcelableExtra(ARTICLE_EXTRA) ?: Article(
            "", "", "",
            User("", "", "")
        )
}