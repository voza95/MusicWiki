package com.example.musicwiki.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.Window
import android.widget.Toast
import com.example.musicwiki.R
import com.google.android.material.snackbar.Snackbar
import java.util.regex.Pattern

object AppUtil {
    fun onDispalySnack(view: View, toastString: String, context: Context) {
        try {
            val snack = Snackbar.make(view, toastString, Snackbar.LENGTH_SHORT)
            snack.show()
        } catch (e: Throwable) {
            try {
                Toast.makeText(context, "" + toastString, Toast.LENGTH_SHORT).show()
            } catch (ee: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun isEmailValid(email: String?): Boolean {
        var isValid = false
        val expression = """^[\w.-]+@([\w\-]+\.)+[A-Z]{2,4}$"""
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (matcher.matches()) {
            isValid = true
        }
        return isValid
    }

    private lateinit var loaderDialog: Dialog
    private fun showLoader(activityContext: Context, isCancel: Boolean) {
        try {
            // Dialog instance for loader;
            if (!this::loaderDialog.isInitialized) {
                loaderDialog = Dialog(activityContext)
                loaderDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                loaderDialog.setCancelable(isCancel)
                loaderDialog.setCanceledOnTouchOutside(isCancel)
                loaderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                loaderDialog.setContentView(R.layout.progress_layout)
                loaderDialog.show()
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    fun cancelDialog() {
        try {
            if (loaderDialog != null && this::loaderDialog.isInitialized) {
                loaderDialog.cancel()
                loaderDialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun hideLoader() {
        if (loaderDialog != null) {
            if (loaderDialog.isShowing) {
                loaderDialog.cancel()
                loaderDialog.dismiss()
            }
        }
    }

    fun showProgressHUD(context: Context, isCancel: Boolean = false) {
        if (context != null) {
            showLoader(context, isCancel)
        }
    }

    fun showProgress(context: Context, isCancel: Boolean) {
        if (context != null) {
            showLoader(context, isCancel)
        }
    }

    fun hideProgressHud() {
        hideLoader()
    }

}