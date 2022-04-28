package com.example.mvvm_inkotlin.utils

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.mvvm_inkotlin.R

object Utils {

    fun customDialog(context: Context?, title: String, message: String, dialogListener: DialogListener) {
        context?.let { context ->
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setCancelable(true)
            builder.setPositiveButton("OK") { dialogInterface: DialogInterface?, i: Int ->
                dialogListener.onPositiveClick()
                dialogInterface?.dismiss()
            }
            builder.setNegativeButton("NO")
            { dialogInterface: DialogInterface?, i: Int ->
                dialogListener.onNegativeClick()
                dialogInterface?.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    fun customDialogUpdate(context: Context?,view: View,title: String, message: String,layoutInflater: Int,dialogListener: DialogListener) {
        context?.let { context ->
            val builder = AlertDialog.Builder(context)
            builder.setView(view)
            builder.setTitle(title)
            builder.setMessage(message)
            builder.setCancelable(true)
            builder.setPositiveButton("Update") { dialogInterface: DialogInterface?, i: Int ->
                dialogListener.onPositiveClick()
                dialogInterface?.dismiss()
            }
            builder.setNegativeButton("Cancel")
            { dialogInterface: DialogInterface?, i: Int ->
                dialogListener.onNegativeClick()
                dialogInterface?.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    interface DialogListener {
        fun onPositiveClick()
        fun onNegativeClick()
    }
}