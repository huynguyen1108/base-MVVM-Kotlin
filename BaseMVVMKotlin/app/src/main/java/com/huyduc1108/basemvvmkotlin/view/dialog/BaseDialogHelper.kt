package com.huyduc1108.basemvvmkotlin.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog

abstract class BaseDialogHelper {
    abstract val dialogView: View
    abstract val builder: AlertDialog.Builder

    //required bools
    open var cancelable: Boolean = true
    open var isBackgroundTransparent: Boolean = true

    //dialog
    open var dialog: AlertDialog? = null

    //dialog create
    open fun create(): AlertDialog {
        dialog = builder.setCancelable(cancelable).create()

        //very much need for customised dialogs
        if (isBackgroundTransparent) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        return dialog!!
    }

    //cancel listener
    open fun onCancelListener(func: () -> Unit): AlertDialog.Builder? =
        builder.setOnCancelListener { func() }

}