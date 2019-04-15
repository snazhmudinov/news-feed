package com.snazhmudinov.newsfeed.api

import com.snazhmudinov.newsfeed.BuildConfig
import com.snazhmudinov.newsfeed.model.Articles
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"

        const val FIRST_PAGE = 1
        const val PAGE_SIZE = 21
    }

    @GET("top-headlines")
    fun getTopUSHeadlines(
        @Query("country") country: String = "us",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = PAGE_SIZE
    ): Single<Articles>
}