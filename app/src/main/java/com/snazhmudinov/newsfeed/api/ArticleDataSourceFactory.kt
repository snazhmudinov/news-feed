package com.snazhmudinov.newsfeed.api

import androidx.paging.DataSource
import com.snazhmudinov.newsfeed.model.Article

class ArticleDataSourceFactory(private val articleDataSource: ArticleDataSource) : DataSource.Factory<Int, Article>() {

    override fun create(): DataSource<Int, Article> {
        return articleDataSource
    }

}