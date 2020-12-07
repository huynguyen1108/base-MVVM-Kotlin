package com.huyduc1108.basemvvmkotlin.base

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.huyduc1108.basemvvmkotlin.utils.showNotesAlertDialog
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


abstract class BaseActivity<T : ViewDataBinding?, V : ViewModel?> :
    AppCompatActivity(),
    HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    var binding: T? = null
    var viewModel: V? = null

    private lateinit var mReceiver: BroadcastReceiver
    private var networkDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView<T>(this, layoutId())
        viewModel = viewModelClass()?.let { ViewModelProviders.of(this, viewModelFactory)[it] }
        initData()
    }

    protected abstract fun layoutId(): Int

    protected abstract fun viewModelClass(): Class<V>?

    protected open fun initData() {} //    public abstract int getFragmentContainerId();

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onResume() {
        super.onResume()

        createBroadcastReceiverNetwork()
    }

    private fun createBroadcastReceiverNetwork() {
        val intentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        mReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val cm =
                    context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = cm.activeNetworkInfo
                if (!(networkInfo != null && networkInfo.isConnected)) {
                    showDialogNetwork()
                } else {
                    hiddenDialogNetwork()
                }
            }
        }
        registerReceiver(mReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        if (mReceiver != null) {
            unregisterReceiver(mReceiver)
        }
    }

    fun showDialogNetwork() {
        if (networkDialog == null) {
            networkDialog = showNotesAlertDialog {
                cancelable = false
            }
        }

        if (networkDialog != null && !networkDialog?.isShowing!!) {
            networkDialog?.show()
        }
    }

    fun hiddenDialogNetwork() {
        if (networkDialog != null && networkDialog?.isShowing!!) {
            networkDialog?.hide()
        }
    }
}