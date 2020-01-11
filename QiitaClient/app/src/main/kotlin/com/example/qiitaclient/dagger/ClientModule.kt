package com.example.qiitaclient.dagger

import com.example.qiitaclient.client.ArticleClient
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ClientModule {

    @Provides
    @Singleton
    fun provideGson(): Gson =
        GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://qiita.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesArticleClient(retrofit: Retrofit): ArticleClient =
        retrofit.create(ArticleClient::class.java)
}