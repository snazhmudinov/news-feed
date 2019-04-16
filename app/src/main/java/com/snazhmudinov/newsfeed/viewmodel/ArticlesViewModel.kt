package com.snazhmudinov.newsfeed.viewmodel

import androidx.lifecycle.ViewModel
import com.snazhmudinov.newsfeed.repository.NewsRepository


class ArticlesViewModel(repository: NewsRepository) : ViewModel() {

    val articles = repository.articles
}