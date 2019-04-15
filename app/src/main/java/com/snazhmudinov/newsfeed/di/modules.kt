package com.snazhmudinov.newsfeed.di

import com.snazhmudinov.newsfeed.BuildConfig
import com.snazhmudinov.newsfeed.api.ArticleDataSource
import com.snazhmudinov.newsfeed.api.ArticleDataSourceFactory
import com.snazhmudinov.newsfeed.api.NewsService
import com.snazhmudinov.newsfeed.viewmodel.ArticlesViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.greenrobot.eventbus.EventBus
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(NewsService.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        EventBus.builder().throwSubscriberException(BuildConfig.DEBUG).installDefaultEventBus()
    }

    single {
        ArticleDataSourceFactory(get())
    }

    single {
        ArticleDataSource(get())
    }
}

val serviceModule = module {

    single {
        get<Retrofit>().create(NewsService::class.java)
    }
}

val viewModelModule = module {

    viewModel {
        ArticlesViewModel(get())
    }
}