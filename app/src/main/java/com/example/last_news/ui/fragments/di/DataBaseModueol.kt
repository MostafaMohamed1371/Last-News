package com.example.little_lemon.di
import android.content.Context
import androidx.room.Room
import com.example.last_news.ui.fragments.models.retrofit.Constants
import com.example.last_news.ui.fragments.models.retrofit.NewsApi
import com.example.last_news.ui.fragments.models.retrofit.NewsRepsoitry
import com.example.last_news.ui.fragments.models.retrofit.roomDb.ArticelDao
import com.example.last_news.ui.fragments.models.retrofit.roomDb.ArticelDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModueol {

//    @Binds
//    @Singleton
//   abstract fun provideGetDataBase(
//        db: ArticelDatabase
//   ): ArticelDatabase


    @Provides
    @Singleton
    fun getDataBase(@ApplicationContext context: Context): ArticelDatabase {
        return Room.databaseBuilder(
            context,
            ArticelDatabase::class.java,
            "MyNewsDatabase",
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideArtcelDao(dataBaseManager: ArticelDatabase): ArticelDao {
        return dataBaseManager.getArticelDao()
    }



}