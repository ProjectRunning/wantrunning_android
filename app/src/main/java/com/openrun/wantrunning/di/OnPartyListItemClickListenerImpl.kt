package com.openrun.wantrunning.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.feature.home.PartyListAdapter
import javax.inject.Inject

class OnPartyListItemClickListenerImpl @Inject constructor(
    private val activity: FragmentActivity
) : PartyListAdapter.OnPartyListItemClickListener{
    override fun onPartyListItemClick(item: Any) {
        activity.findNavController(R.id.fcv_main_nav_host)
            .navigate(R.id.action_mainFeatureFragment_to_partyDetailFragment)
    }
}