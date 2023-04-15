package com.example.last_news.ui.fragments.models.retrofit.roomDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.last_news.ui.fragments.models.retrofit.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(ArticelTypeConvert::class)
abstract class ArticelDatabase : RoomDatabase(){

    abstract fun getArticelDao(): ArticelDao


//    companion object {
//        private const val DATABASE_NAME = "MyNewsDatabase"
//        @Volatile
//        private var instance: ArticelDatabase? = null
//        fun getinstance(context: Context): ArticelDatabase {
//            return instance ?: synchronized(context) { buildDatabase(context).also { instance = it } }
//        }
//
//        fun getinstancewithout(): ArticelDatabase? {
//            return instance
//
//        }
//
//        private fun buildDatabase(context: Context): ArticelDatabase {
//            return Room.databaseBuilder(context, ArticelDatabase::class.java, DATABASE_NAME
//            ).fallbackToDestructiveMigration()
//                .allowMainThreadQueries()
//                .build()
//
//        }
//    }
}