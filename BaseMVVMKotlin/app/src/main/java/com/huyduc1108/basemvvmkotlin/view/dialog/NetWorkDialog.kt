package com.huyduc1108.basemvvmkotlin.view.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.huyduc1108.basemvvmkotlin.R
import javax.inject.Inject

class NetWorkDialog @Inject constructor(context: Context) : BaseDialogHelper() {

    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.network_layout, null)
    }

    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)

//    private val okBtn: Button by lazy {
//        dialogView.findViewById<Button>(R.id.ok_btn)
//    }


    fun closeIconClickListener(func: (() -> Unit)? = null) {
//        with(okBtn) {
//            setClickListenerToDialogIcon(func)
//        }
    }

//    fun doneIconClickListener(func: (() -> Unit)? = null) =
//        with(okBtn) {
//            setClickListenerToDialogIcon(func)
//        }

    private fun View.setClickListenerToDialogIcon(func: (() -> Unit)?) =
        setOnClickListener {
            func?.invoke()
            dialog?.dismiss()
        }
}