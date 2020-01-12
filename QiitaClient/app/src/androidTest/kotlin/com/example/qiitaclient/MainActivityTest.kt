package com.example.qiitaclient

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.qiitaclient.client.ArticleClient
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import rx.Observable

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @JvmField
    @Rule
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun 各ビューが表示されていること_ただしプログレスバーは非表示() {
        onView(withId(R.id.list_view)).check(matches(isDisplayed()))
        onView(withId(R.id.query_edit_text)).check(matches(isDisplayed()))
        onView(withId(R.id.search_button)).check(matches(isDisplayed()))

        onView(withId(R.id.progress_bar)).check(matches(isNotDisplayed()))
    }

    @Test
    fun `検索ボタンがタップされたら、入力されたクエリ文字列で記事検索APIを叩くこと`() {
        // 検索するクエリ文字列
        val query = "user:ngsw_taro"

        // ArticleClient の mock 設定
        val articleClient = mock(ArticleClient::class.java).apply {
            `when`(search(query)).thenReturn(Observable.just(listOf()))
        }
        activityTestRule.activity.articleClient = articleClient

        // UI操作を設定
        onView(withId(R.id.query_edit_text)).perform(typeText(query))
        onView(withId(R.id.search_button)).perform(click())

        // search の引数を verify
        verify(articleClient).search(query)
    }

    private fun isNotDisplayed(): Matcher<View> = not(isDisplayed())
}