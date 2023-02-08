package com.openrun.wantrunning.util.extension

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.fragment.app.Fragment

@SuppressLint("DiscouragedApi", "InternalInsetResource")
fun Fragment.getStatusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}

fun Fragment.makeToast(message: String, duration: Int) {
    try {
        val context = this.context ?: requireContext()
        Toast.makeText(context, message, duration).show()
    } catch (e: IllegalStateException) {
        return
    }
}
