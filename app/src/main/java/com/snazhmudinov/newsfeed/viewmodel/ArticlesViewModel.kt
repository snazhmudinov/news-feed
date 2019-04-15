package com.snazhmudinov.newsfeed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.snazhmudinov.newsfeed.api.ArticleDataSourceFactory
import com.snazhmudinov.newsfeed.api.NewsService


class ArticlesViewModel(articleDataSourceFactory: ArticleDataSourceFactory) : ViewModel() {

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(NewsService.PAGE_SIZE)
        .build()

    val articles = LivePagedListBuilder(articleDataSourceFactory, config).build()

}