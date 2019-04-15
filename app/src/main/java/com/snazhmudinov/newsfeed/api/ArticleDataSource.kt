package com.snazhmudinov.newsfeed.api

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import com.snazhmudinov.newsfeed.api.NewsService.Companion.FIRST_PAGE
import com.snazhmudinov.newsfeed.model.Article
import io.reactivex.schedulers.Schedulers

class ArticleDataSource(private val service: NewsService) : PageKeyedDataSource<Int, Article>() {

    private var totalRes = 0

    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
        service.getTopUSHeadlines(page = FIRST_PAGE)
            .subscribeOn(Schedulers.io())
            .subscribe { articles, _ ->
                totalRes = articles.totalResults
                articles?.articles?.let { callback.onResult(it, null, FIRST_PAGE) }
            }
    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        service.getTopUSHeadlines(page = params.key)
            .subscribeOn(Schedulers.io())
            .subscribe { articles, _ ->
                val adjacentKey = if (params.key < totalRes) params.key + 1 else null
                articles?.articles?.let { callback.onResult(it, adjacentKey) }
            }
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        service.getTopUSHeadlines(page = params.key)
            .subscribeOn(Schedulers.io())
            .subscribe { articles, _ ->
                val adjacentKey = if (params.key > 1) params.key - 1 else null
                articles?.articles?.let { callback.onResult(it, adjacentKey) }
            }
    }
}