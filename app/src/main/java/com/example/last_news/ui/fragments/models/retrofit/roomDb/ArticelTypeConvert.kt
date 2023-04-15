package com.example.last_news.ui.fragments.models.retrofit.roomDb

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.last_news.ui.fragments.models.retrofit.Source

@TypeConverters
class ArticelTypeConvert {
    @TypeConverter
    fun fromSource(source: Source):String{
       return source.name.toString()
    }

    @TypeConverter
    fun toSource(name:String):Source{
       return Source(name,name)
    }
}