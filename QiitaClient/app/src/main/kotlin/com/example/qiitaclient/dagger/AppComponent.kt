package com.example.qiitaclient.dagger

import com.example.qiitaclient.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ClientModule::class]) // 依存性(bean)作成モジュールの指定
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity) // 依存性注入先として MainActivity を指定
}