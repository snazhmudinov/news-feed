package com.snazhmudinov.newsfeed.application

import android.app.Application
import com.snazhmudinov.newsfeed.di.appModule
import com.snazhmudinov.newsfeed.di.repoModule
import com.snazhmudinov.newsfeed.di.serviceModule
import com.snazhmudinov.newsfeed.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApplication)
            modules(appModule, repoModule, serviceModule, viewModelModule)
        }
    }
}