package com.snazhmudinov.newsfeed.event

import com.snazhmudinov.newsfeed.model.Article

data class ArticleClickedEvent(val article: Article)