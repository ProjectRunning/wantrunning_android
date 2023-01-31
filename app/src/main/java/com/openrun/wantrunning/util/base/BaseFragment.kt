package com.openrun.wantrunning.util.base

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.util.extension.getStatusBarHeight
import com.openrun.wantrunning.util.extension.setMargins
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    open var fullScreen: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        controlFullScreen()
    }

    private fun controlFullScreen() {
        Log.d("BaseFragment", "controlFullScreen: $fullScreen")
        if (fullScreen) {
            activity?.window?.statusBarColor = Color.TRANSPARENT
            view?.setMargins(0, 0, 0, 0)
        } else {
            activity?.window?.statusBarColor = Color.WHITE
            view?.setMargins(0, getStatusBarHeight(), 0, 0)
        }
    }
}