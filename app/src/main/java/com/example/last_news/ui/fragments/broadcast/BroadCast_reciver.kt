package com.example.lastorderfood.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.example.last_news.R
import io.github.muddz.styleabletoast.StyleableToast


class BroadCast_reciver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            // Device has internet connection
            StyleableToast.makeText(context,"Device has internet connection", R.style.exampleToast).show()

        } else {
            // Device does not have internet connection
            StyleableToast.makeText(context,"Device does not have internet connection", R.style.exampleToast).show()

        }
    }
}