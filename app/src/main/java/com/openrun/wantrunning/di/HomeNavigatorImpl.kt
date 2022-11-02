package com.openrun.wantrunning.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.feature.home.HomeNavigator
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(
    private val activity: FragmentActivity
): HomeNavigator {

    override fun navigateToMakeRunningParty() {
        activity.findNavController(R.id.fcv_main_nav_host)
            .navigate(R.id.action_mainFeatureFragment_to_makeRunningPartyFragment)
    }
}