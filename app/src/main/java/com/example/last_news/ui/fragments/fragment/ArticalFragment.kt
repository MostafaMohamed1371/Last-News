package com.example.last_news.ui.fragments.fragment

import android.app.ProgressDialog.show
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.navigation.navArgument
import com.example.last_news.R
import com.example.last_news.databinding.FragmentArticalBinding
import com.example.last_news.ui.fragments.activity.NewsActivity
import com.example.last_news.ui.fragments.models.retrofit.Article
import com.example.last_news.ui.fragments.viewModel.BreackingViewModel
import com.google.android.material.snackbar.Snackbar
import io.github.muddz.styleabletoast.StyleableToast
import kotlinx.android.synthetic.main.fragment_artical.*
import retrofit2.Response.error
import java.lang.reflect.InvocationTargetException


class ArticalFragment : Fragment() {
    private lateinit var binding: FragmentArticalBinding
    private lateinit var articelMvvm: BreackingViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        articelMvvm=(activity as NewsActivity).viewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding= FragmentArticalBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // try {
            val args:ArticalFragmentArgs by navArgs()
            val atrical=args.artical
            webView.apply {
                webViewClient= WebViewClient()
                loadUrl(atrical.url.toString())
            }
       // } catch (ex: InvocationTargetException)
       // { Log.e("oops!", ex.cause.toString()) }
        binding.btnAddFav.setOnClickListener {
            atrical.let {News->
                if (News != null) {
                    articelMvvm.upinsertNews(News)
                    StyleableToast.makeText(requireActivity(),"artical saved",R.style.exampleToast).show()

                }

            }

        }

    }



}