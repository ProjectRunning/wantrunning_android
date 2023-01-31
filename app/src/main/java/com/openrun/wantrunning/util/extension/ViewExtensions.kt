package com.openrun.wantrunning.util.extension

import android.view.View
import android.view.ViewGroup

fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        (layoutParams as ViewGroup.MarginLayoutParams)
            .setMargins(left, top, right, bottom)
        requestLayout()
    }
}
