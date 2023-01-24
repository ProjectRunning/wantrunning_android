package com.openrun.wantrunning

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.openrun.wantrunning.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_main_nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMainNav.setupWithNavController(navController = navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            onDestinationChanged(destination = destination)
        }
    }

    private fun onDestinationChanged(destination: NavDestination) {
        controlBottomNavigationViewVisibility(destination = destination)
        controlStatusBar(destination = destination)
    }

    private fun controlBottomNavigationViewVisibility(destination: NavDestination) {
        val mainScreens = listOf(
            R.id.mainHomeFragment,
            R.id.mainRunningFragment,
            R.id.mainHistoryFragment,
            R.id.mainProfileFragment
        )
        if (mainScreens.contains(destination.id) && !binding.bnvMainNav.isVisible) {
            showBottomNavigationView()
        } else if (!mainScreens.contains(destination.id) && binding.bnvMainNav.isVisible) {
            hideBottomNavigationView()
        } else {
            binding.bnvMainNav.isVisible = mainScreens.contains(destination.id)
        }
    }

    private fun controlStatusBar(destination: NavDestination) {
        val fullScreen = listOf(R.id.mainRunningFragment)
        if (fullScreen.contains(destination.id)) {
            setStatusBarTransparent()
        } else {
            setStatusBarOrigin()
        }
    }

    private fun showBottomNavigationView() {
        val showAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_up_duration_300)
        showAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.bnvMainNav.visibility = View.VISIBLE
            }

            override fun onAnimationEnd(p0: Animation?) {}

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.bnvMainNav.startAnimation(showAnimation)
    }

    private fun hideBottomNavigationView() {
        val hideAnimation = AnimationUtils.loadAnimation(this, R.anim.anim_slide_down_duration_300)
        hideAnimation.setAnimationListener(object : AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                binding.bnvMainNav.visibility = View.GONE
            }

            override fun onAnimationEnd(p0: Animation?) {}

            override fun onAnimationRepeat(p0: Animation?) {}
        })
        binding.bnvMainNav.startAnimation(hideAnimation)
    }

    private fun setStatusBarTransparent() {
        val window = window ?: return

        window.statusBarColor = Color.TRANSPARENT
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setWindowFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
    }

    private fun setStatusBarOrigin() {
        val window = window ?: return

        window.statusBarColor = Color.WHITE
        window.decorView
    }
}
