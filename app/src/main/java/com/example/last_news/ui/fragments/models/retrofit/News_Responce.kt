package com.example.last_news.ui.fragments.models.retrofit


import com.google.gson.annotations.SerializedName

data class News_Responce(
    @SerializedName("articles")
    val articles: List<Article?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null
)