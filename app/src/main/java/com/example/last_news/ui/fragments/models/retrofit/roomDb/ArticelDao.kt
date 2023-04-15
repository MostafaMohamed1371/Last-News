package com.example.last_news.ui.fragments.models.retrofit.roomDb
import androidx.room.OnConflictStrategy.REPLACE
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.last_news.ui.fragments.models.retrofit.Article

@Dao
interface ArticelDao {
    @Insert(onConflict = REPLACE)
    suspend fun upsert(articel: Article)
    @Delete
    suspend fun delete(articel: Article)
    @Query("SELECT * FROM Articles")
    fun getAllArticles(): LiveData<List<Article>>
}