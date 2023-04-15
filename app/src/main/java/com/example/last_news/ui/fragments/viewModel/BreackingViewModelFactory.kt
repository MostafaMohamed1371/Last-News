package com.example.last_news.ui.fragments.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.last_news.ui.fragments.models.retrofit.NewsRepsoitry

class BreackingViewModelFactory (
    private val newsRepositry: NewsRepsoitry
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BreackingViewModel(newsRepositry) as T
    }
}