package com.snazhmudinov.newsfeed.repository

import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.snazhmudinov.newsfeed.api.ArticleDataSourceFactory
import com.snazhmudinov.newsfeed.api.NewsService

class NewsRepository(articleDataSourceFactory: ArticleDataSourceFactory) {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(NewsService.PAGE_SIZE)
        .build()

    val articles = LivePagedListBuilder(articleDataSourceFactory, config).build()
}