package com.example.last_news.ui.fragments.di

import com.example.last_news.ui.fragments.models.retrofit.Constants
import com.example.last_news.ui.fragments.models.retrofit.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModueol {

    @Provides
    @Singleton
    fun provideHttp(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            . retryOnConnectionFailure(true)
            .readTimeout(30, TimeUnit.SECONDS)
            . connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()

    }

    @Provides
    @Singleton
    fun retrofit(okHttpClient:OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

}