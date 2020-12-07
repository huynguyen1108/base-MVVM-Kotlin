package com.huyduc1108.basemvvmkotlin.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkInfo


object ConnectivityUtil {
    fun isConnected(context: Context): Boolean {
        var connected: Boolean = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.Q) {
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            return activeNetwork?.isConnectedOrConnecting == true
        } else {
            try {
                cm.registerDefaultNetworkCallback(object : NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        connected = true
                    }

                    override fun onLost(network: Network) {
                        connected = false // Global Static Variable
                    }
                }
                )
                connected = false
            } catch (e: Exception) {
                connected = false
            } finally {
                return connected
            }
        }

    }
}