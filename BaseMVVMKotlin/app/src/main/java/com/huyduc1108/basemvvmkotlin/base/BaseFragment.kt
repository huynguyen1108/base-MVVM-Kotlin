package com.huyduc1108.basemvvmkotlin.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil.inflate
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.huyduc1108.basemvvmkotlin.di.Injectable
import com.huyduc1108.basemvvmkotlin.utils.showLoadingAlertDialog
import java.util.*
import javax.inject.Inject

open abstract class BaseFragment<T : ViewDataBinding?, V : ViewModel?> :
    Fragment(), Injectable {
    @JvmField
    @Inject
    var viewModelFactory: ViewModelProvider.Factory? = null
    var binding: T? = null
    var viewModel: V? = null
    private var loadingDialog: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = viewModelClass()?.let { ViewModelProviders.of(this, viewModelFactory)[it] }
        binding = inflate<T>(inflater, layoutId(), container, false)
//        binding?.lifecycleOwner = this.viewLifecycleOwner
        return binding?.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            parseArgs(arguments)
        }
        initView()
        initObserver()
        initData()
    }

    protected open fun parseArgs(args: Bundle?) {}

    protected abstract fun layoutId(): Int

    protected abstract fun viewModelClass(): Class<V>?

    fun setData(data: HashMap<String?, Any>?) {
        if (data == null || data.isEmpty()) {
            arguments = Bundle()
            return
        }
        val bundle = Bundle()
        for ((key, value) in data) {
            if (value is String) {
                bundle.putString(key, value)
            } else if (value is Double) {
                bundle.putDouble(key, value)
            } else if (value is Int) {
                bundle.putInt(key, value)
            } else if (value is Float) {
                bundle.putFloat(key, value)
            } else if (value is Boolean) {
                bundle.putBoolean(key, value)
            } else if (value is Parcelable) {
                bundle.putParcelable(key, value)
            } else if (value is Array<*>) {
                bundle.putStringArray(key, value as Array<String?>)
            } else if (value is ArrayList<*>) {
                if (value.size > 0 && value[0] is String
                ) {
                    bundle.putStringArrayList(
                        key,
                        value as ArrayList<String?>
                    )
                } else if (value.size > 0 && value[0] is Parcelable
                ) {
                    bundle.putParcelableArrayList(
                        key,
                        value as ArrayList<out Parcelable?>
                    )
                }
            }
        }
        arguments = bundle
    }

    protected open fun initView() {}

    protected open fun initObserver() {}

    protected open fun initData() {}

    fun showDialogLoading() {
        if (loadingDialog == null) {
            loadingDialog = showLoadingAlertDialog {
                cancelable = false
            }
        }
        loadingDialog?.show()
    }

    fun hiddenDialogLoading() {
        if (loadingDialog == null) {
            return
        }
        loadingDialog?.dismiss()
    }
}