package com.example.last_news.ui.fragments.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.last_news.R
import com.example.last_news.databinding.FragmentSavedBinding
import com.example.last_news.databinding.FragmentSearchBinding
import com.example.last_news.ui.fragments.activity.NewsActivity
import com.example.last_news.ui.fragments.adapter.FavoriteAdapter
import com.example.last_news.ui.fragments.viewModel.BreackingViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var searchMvvm: BreackingViewModel
    private lateinit var searchAdapter:FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchMvvm=(activity as NewsActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentSearchBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        PrepareAdapter()
        binding.arrowsearch.setOnClickListener {
            searchNews()
        }

        observeSearchLiveData()

        var searchJob: Job?=null
        binding.boxsearch.addTextChangedListener{search->
            searchJob?.cancel()
            searchJob=lifecycleScope.launch {
                delay(250)
                searchMvvm.getSearchNews(search.toString())
            }
        }
        onNewsClick()
    }
    private fun observeSearchLiveData() {
        searchMvvm.observeSearchNewssLiveData().observe(viewLifecycleOwner, Observer{
                newsList->searchAdapter.differ.submitList(newsList)
        })
    }

    private fun searchNews() {
        val search=binding.boxsearch.text.toString()
        if (search.isNotEmpty()){
            searchMvvm.getSearchNews(search)
        }
    }

    private fun PrepareAdapter() {
        searchAdapter= FavoriteAdapter()
        binding.searchrecyclerView.apply {
            layoutManager= GridLayoutManager(context,3, GridLayoutManager.VERTICAL,false)
            adapter=searchAdapter
        }
    }

    private fun onNewsClick() {
        searchAdapter.onItemClick={
            val bundle=Bundle().apply {
                putSerializable("artical",it)
            }
            findNavController().navigate(R.id.action_searchFragment_to_articalFragment,bundle)
        }

    }


}