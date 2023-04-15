package com.example.last_news.ui.fragments.models.retrofit


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Source(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String? = null
)