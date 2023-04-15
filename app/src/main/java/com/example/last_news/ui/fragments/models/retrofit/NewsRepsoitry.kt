package com.example.last_news.ui.fragments.models.retrofit

import com.example.last_news.ui.fragments.models.retrofit.roomDb.ArticelDatabase
import javax.inject.Inject

class NewsRepsoitry @Inject constructor(
    val db:ArticelDatabase,val newsApi:NewsApi
) {
    suspend fun getBreackingNews(country:String,pageNumber:Int)=newsApi.getBreackingNews(country,pageNumber)
    suspend fun getSearchNews(country:String,pageNumber:Int)=newsApi.searchForNews(country,pageNumber)

}