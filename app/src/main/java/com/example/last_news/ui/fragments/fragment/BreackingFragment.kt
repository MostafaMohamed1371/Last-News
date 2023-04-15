package com.example.last_news.ui.fragments.fragment

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.last_news.R
import com.example.last_news.databinding.FragmentBreackingBinding
import com.example.last_news.ui.fragments.activity.NewsActivity
import com.example.last_news.ui.fragments.adapter.NewsAdapter
import com.example.last_news.ui.fragments.login.DataLogin
import com.example.last_news.ui.fragments.viewModel.BreackingViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.AndroidEntryPoint
import io.github.muddz.styleabletoast.StyleableToast
import java.io.File
import javax.inject.Inject
import javax.inject.Named
@AndroidEntryPoint
class BreackingFragment : Fragment() {
    private lateinit var binding: FragmentBreackingBinding
    private lateinit var breackingMvvm:BreackingViewModel
    private lateinit var articelAdapter:NewsAdapter
    @Inject
    @Named("imageStorage")
     lateinit var imageStorage: StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        breackingMvvm=(activity as NewsActivity).viewModel
      //  imageStorage= FirebaseStorage.getInstance().getReferenceFromUrl("gs://last-news-dac0b.appspot.com/").child(FirebaseAuth.getInstance().currentUser!!.uid)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentBreackingBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareArticelItemsRecyclerView()
        breackingMvvm.getBreacking("us")
        observerArticelItems()
        binding.imageSearch.setOnClickListener {
            findNavController().navigate(R.id.action_breackingFragment_to_searchFragment)
        }
        onNewsClick()
        onLogoutIconClick()
        binding.userImage.setOnClickListener {
            findNavController().navigate(R.id.action_breackingFragment_to_profileFragment)
        }
        getImageFromFirebase()
    }

    private fun prepareArticelItemsRecyclerView() {
        articelAdapter=NewsAdapter()
        binding.articelRecyclerView.apply {
            layoutManager= LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
            adapter=articelAdapter
        }
    }

    private fun observerArticelItems() {
        breackingMvvm.observeBreackingLiveData().observe(requireActivity(), Observer {

            articelAdapter.differ.submitList(it)

        })

    }
    private fun onNewsClick() {
        articelAdapter.onItemClick={
            val bundle=Bundle().apply {
                putSerializable("artical",it)
            }
            findNavController().navigate(R.id.action_breackingFragment_to_articalFragment,bundle)
        }

    }

        private fun onLogoutIconClick() {
        binding.imageLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_breackingFragment_to_loginFragment)

        }
    }
    private  fun  getImageFromFirebase() {
        val file: File = File.createTempFile("image","jpeg")

        imageStorage.getFile(file).addOnSuccessListener {
            val  bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)
            binding.userImage.setImageBitmap(bitmap)
        }.addOnFailureListener {
            StyleableToast.makeText(requireActivity(),"Image failed to load",
              R.style.exampleToast).show()
        }
    }




}