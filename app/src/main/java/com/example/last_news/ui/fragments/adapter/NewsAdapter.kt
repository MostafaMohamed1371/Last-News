package com.example.last_news.ui.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.last_news.R
import com.example.last_news.databinding.FragmentBreackingBinding
import com.example.last_news.databinding.ItemArticalBinding
import com.example.last_news.ui.fragments.models.retrofit.Article

class NewsAdapter (): RecyclerView.Adapter<NewsAdapter.MyviewHolder>(){

    lateinit var onItemClick:((Article)->Unit)
    private val diffUtil=object : DiffUtil.ItemCallback<Article>(){

        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem==newItem
        }

    }
    val differ= AsyncListDiffer(this,diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        return MyviewHolder(ItemArticalBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(differ.currentList[position].urlToImage)
            .into(holder.binding.imageArticel)
        holder.binding.textSource.text=differ.currentList[position].source!!.name
            holder.binding.textPublish.text=differ.currentList[position].publishedAt
            holder.binding.textTitle.text=differ.currentList[position].title
            holder.binding.textDescription.text=differ.currentList[position].description
        holder.itemView.setOnClickListener {
            onItemClick.invoke(differ.currentList[position])
        }

    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class  MyviewHolder(var binding: ItemArticalBinding) : RecyclerView.ViewHolder(binding.root)

}


