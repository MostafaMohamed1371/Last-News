package com.example.last_news.ui.fragments.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.last_news.ui.fragments.models.retrofit.Article
import com.example.last_news.ui.fragments.models.retrofit.NewsRepsoitry
import com.example.last_news.ui.fragments.models.retrofit.News_Responce
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class BreackingViewModel @Inject constructor(
    val newsRepositry:NewsRepsoitry
): ViewModel()  {
    private var breackingLiveData= MutableLiveData<List<Article?>?>()
    private var searchNewssLiveData=MutableLiveData<List<Article>?>()
    private  var favoriteNewsLiveData=newsRepositry.db.getArticelDao().getAllArticles()
    fun getBreacking(country:String) {
       val pageNumber=1
        viewModelScope.launch {
            newsRepositry.getBreackingNews(country,pageNumber).enqueue(object : retrofit2.Callback<News_Responce> {
                override fun onResponse(call: Call<News_Responce>, response: Response<News_Responce>) {
                    if (response.body() != null) {
                        var randomBreacking: List<Article?>? = response.body()!!.articles
                        breackingLiveData.value=randomBreacking
                        Log.e("TAG", "$randomBreacking: ", )


                    }
                }

                override fun onFailure(call: Call<News_Responce>, t: Throwable) {
                    Log.d("BreackingFragment", t.message.toString())
                }

            })
        }

    }

    fun getSearchNews(search:String) {
        val pageNumber=1
        viewModelScope.launch {
            newsRepositry.getSearchNews(search,pageNumber).enqueue(object : retrofit2.Callback<News_Responce> {
                override fun onResponse(
                    call: Call<News_Responce>,
                    response: Response<News_Responce>
                ) {
                    if (response.body() != null) {
                        val search = response.body()?.articles
                        search?.let {
                            searchNewssLiveData.postValue(it as List<Article>)
                        }

                    }
                }

                override fun onFailure(call: Call<News_Responce>, t: Throwable) {
                    Log.d("BreackingFragment", t.message.toString())
                }

            })
        }
    }

    fun observeBreackingLiveData(): MutableLiveData<List<Article?>?> {
        return breackingLiveData
    }

    fun observeSearchNewssLiveData(): LiveData<List<Article>?> {
        return searchNewssLiveData
    }
    fun observeFavoriteNewsLiveData(): LiveData<List<Article>> {
        return favoriteNewsLiveData
    }

    fun upinsertNews(article: Article){
        viewModelScope.launch {
            newsRepositry.db.getArticelDao().upsert(article)
        }
    }

    fun deleteNews(article: Article){
        viewModelScope.launch {
            newsRepositry.db.getArticelDao().delete(article)
        }
    }
}