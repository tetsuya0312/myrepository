package com.example.qiitaclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ProgressBar
import com.example.qiitaclient.client.ArticleClient
import com.example.qiitaclient.model.Article
import com.example.qiitaclient.model.User
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : RxAppCompatActivity() {

    /** WebAPIクライアント */
    @Inject // 依存性の注入
    lateinit var articleClient: ArticleClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as QiitaClientApp).component.inject(this)
        setContentView(R.layout.activity_main)

        // View オブジェクトの取得
        val listView = findViewById<ListView>(R.id.list_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val queryEditText = findViewById<EditText>(R.id.query_edit_text)
        val searchButton = findViewById<Button>(R.id.search_button)

        val listAdapter = ArticleListAdpter(applicationContext)
        listView.adapter = listAdapter
        // 記事画面遷移処理の設定
        listView.setOnItemClickListener { _, _, position, _ ->
            val article = listAdapter.articles[position]
            ArticleActivity.intent(this, article).let { startActivity(it) }
        }

        // 検索処理の設定
        searchButton.setOnClickListener {
            // プログレスバーを表示
            progressBar.visibility = View.VISIBLE

            // 検索文字列を使用し、WebAPI にリクエスト送信
            articleClient.search(queryEditText.text.toString())
                // APIアクセス、JSONからの変換をバックグラウンドスレッドで行うよう設定
                .subscribeOn(Schedulers.io())
                // 以降のコールバックをメインスレッド(UIスレッド)で行うよう設定
                .observeOn(AndroidSchedulers.mainThread())
                // ストリームが終端に達した後に呼び出されるコールバックを登録
                .doAfterTerminate {
                    // 処理完了時にプログレスバーを非表示にする
                    progressBar.visibility = View.GONE
                }
                // RxLifecycle にライフサイクルの面倒を見てもらう
                .bindToLifecycle(this)
                // レスポンスを受け取った際に行う処理をコールバックとして登録
                .subscribe({
                    // 成功時の処理
                    // 検索テキストボックスを空にして、
                    queryEditText.text.clear()
                    // 記事リストを差し替えて、
                    listAdapter.articles = it
                    // 変更を画面に反映する
                    listAdapter.notifyDataSetChanged()
                }, {
                    // 失敗時の処理
                    toast("エラー： $it")
                })
        }
    }

    // ダミー記事を生成するメソッド
    private fun dummyArticle(title: String, username: String): Article =
        Article(
            id = "",
            title = title,
            url = "https://kotlinlang.org/",
            user = User(id = "", name = username, profileImageUrl = "")
        )
}
