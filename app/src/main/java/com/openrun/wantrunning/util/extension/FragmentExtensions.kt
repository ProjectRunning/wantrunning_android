package com.openrun.wantrunning.util.extension

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment

@SuppressLint("DiscouragedApi", "InternalInsetResource")
fun Fragment.getStatusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    return if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
}
