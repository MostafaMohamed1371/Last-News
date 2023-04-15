package com.example.little_lemon.di

import com.example.last_news.ui.fragments.models.retrofit.NewsApi
import com.example.last_news.ui.fragments.models.retrofit.NewsRepsoitry
import com.example.last_news.ui.fragments.models.retrofit.roomDb.ArticelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ViewModelMudeol {


    @Provides
    @Singleton
     fun provideGetRepositry(db: ArticelDatabase,newsApi:NewsApi): NewsRepsoitry {
        return NewsRepsoitry(db,newsApi)
    }
}