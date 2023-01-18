package com.openrun.wantrunning.util

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.makeToast(message: String, duration: Int) {
    try {
        val context = this.context ?: requireContext()
        Toast.makeText(context, message, duration).show()
    } catch (e: IllegalStateException) {
        return
    }
}