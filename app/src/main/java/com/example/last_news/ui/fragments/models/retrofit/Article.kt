package com.example.last_news.ui.fragments.models.retrofit


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Articles")
data class Article(
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("source")
    val source: Source? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null
):Serializable