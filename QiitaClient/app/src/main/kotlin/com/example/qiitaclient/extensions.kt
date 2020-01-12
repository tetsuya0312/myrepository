package com.example.qiitaclient

import android.content.Context
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

fun <T : View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById<T>(id)
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, duration).show()

@BindingAdapter("bind:imageUrl")
fun ImageView.loadImage(url: String) =
    Glide.with(context).load(url).into(this)

@BindingAdapter("bind:loadUrl")
fun WebView.loadUrl(url: String) =
    // 拡張関数とメソッドが同名の場合、常にメソッドが優先される
    loadUrl(url)
