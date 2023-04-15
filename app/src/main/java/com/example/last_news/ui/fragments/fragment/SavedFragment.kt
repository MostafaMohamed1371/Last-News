package com.example.last_news.ui.fragments.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.last_news.R
import com.example.last_news.databinding.FragmentBreackingBinding
import com.example.last_news.databinding.FragmentSavedBinding
import com.example.last_news.ui.fragments.activity.NewsActivity
import com.example.last_news.ui.fragments.adapter.FavoriteAdapter
import com.example.last_news.ui.fragments.viewModel.BreackingViewModel
import com.google.android.material.snackbar.Snackbar


class SavedFragment : Fragment() {
    private lateinit var binding: FragmentSavedBinding
    private lateinit var savedMvvm: BreackingViewModel
    private lateinit var favoritAdapter: FavoriteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedMvvm=(activity as NewsActivity).viewModel

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentSavedBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemTouchHelper=object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            )=true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position=viewHolder.adapterPosition
                val article=favoritAdapter.differ.currentList[position]
                savedMvvm.deleteNews(article)
                Snackbar.make(view,"artical deleted", Snackbar.LENGTH_SHORT).apply {
                    setAction("Undo")
                    {
                        savedMvvm.upinsertNews(article)

                    }
                    show()
                }
            }

        }
        ItemTouchHelper(itemTouchHelper).attachToRecyclerView(binding.recyclerViewSave)
        PrepareAdapter()
        observeFavoritLiveData()
        onNewsClick()

    }
    private fun observeFavoritLiveData() {
        savedMvvm.observeFavoriteNewsLiveData().observe(viewLifecycleOwner, Observer{
                newsList->favoritAdapter.differ.submitList(newsList)
        })
    }


    private fun PrepareAdapter() {
        favoritAdapter= FavoriteAdapter()
        binding.recyclerViewSave.apply {
            layoutManager= GridLayoutManager(context,3, GridLayoutManager.VERTICAL,false)
            adapter=favoritAdapter
        }
    }

    private fun onNewsClick() {
        favoritAdapter.onItemClick={
            val bundle=Bundle().apply {
                putSerializable("artical",it)
            }
            findNavController().navigate(R.id.action_savedFragment_to_articalFragment,bundle)
        }

    }



}