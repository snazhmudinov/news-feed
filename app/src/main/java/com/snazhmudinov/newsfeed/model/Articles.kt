package com.snazhmudinov.newsfeed.model

import com.squareup.moshi.Json

data class Articles(
    @Json(name = "articles")
    val articles: List<Article>,
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int
)