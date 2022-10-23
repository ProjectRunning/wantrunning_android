package com.openrun.wantrunning.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.feature.landing.signin.SignInButtonClick
import javax.inject.Inject

class SignInButtonClickImpl @Inject constructor(
    private val activity: FragmentActivity
) : SignInButtonClick {

    override fun kakaoSignIn() {
        activity.findNavController(R.id.fcv_main_nav_host)
            .navigate(R.id.action_landingNavHostFragment_to_mainFeatureFragment)
    }
}