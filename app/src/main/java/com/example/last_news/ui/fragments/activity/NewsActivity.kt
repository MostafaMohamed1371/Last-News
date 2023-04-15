package com.example.last_news.ui.fragments.activity

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.last_news.R
import com.example.last_news.databinding.ActivityNewsBinding
import com.example.last_news.ui.fragments.models.retrofit.NewsRepsoitry
import com.example.last_news.ui.fragments.models.retrofit.roomDb.ArticelDatabase
import com.example.last_news.ui.fragments.viewModel.BreackingViewModel
import com.example.last_news.ui.fragments.viewModel.BreackingViewModelFactory
import com.example.lastorderfood.broadcast.BroadCast_reciver
import com.example.lastorderfood.service.service_forground
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_news.*

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {
    val viewModel: BreackingViewModel by lazy {
       // val newsRepsoitry= NewsRepsoitry(ArticelDatabase.getinstance(this))
       // val viewModelFactory= BreackingViewModelFactory(newsRepsoitry)
        ViewModelProvider(this)[BreackingViewModel::class.java]
    }
    private val navController by lazy {
        Navigation.findNavController(this,R.id.sub_fragment)
    }
    private lateinit var binding: ActivityNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val navcontroller=findNavController(R.id.sub_fragment)
        binding.bottomNav.setupWithNavController(navcontroller)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.splashFragment||destination.id == R.id.loginFragment||destination.id == R.id.registerFragment) {
                bottom_nav.visibility= View.GONE
            }else{
                bottom_nav.visibility= View.VISIBLE
            }
        }
       //service_forground
        val intent= Intent(this, service_forground::class.java)
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            startForegroundService(intent)
        }
        else{
            startService(intent)
        }

        //BroadCast_reciver
        val broadcast= BroadCast_reciver()
        val filter= IntentFilter()
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(broadcast,filter)

    }

}