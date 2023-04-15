package com.example.last_news.ui.fragments.models.retrofit

import com.example.last_news.ui.fragments.models.retrofit.Constants.Companion.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("v2/top-headlines")
  fun getBreackingNews(
        @Query("country")
        countryCode:String="us",
        @Query("page")
         pageNumber:Int=10,
        @Query("apiKey")
        apiKey:String=API_KEY
    ): Call<News_Responce>

    @GET("v2/everything")
     fun searchForNews(
        @Query("q")
        searchQuery:String,
        @Query("page")
        pageNumber:Int=10,
        @Query("apiKey")
        apiKey:String=API_KEY
    ):Call<News_Responce>
}