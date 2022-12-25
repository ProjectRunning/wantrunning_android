package com.openrun.wantrunning.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.feature.home.PartyDetailFragment
import javax.inject.Inject

class OnPartyMemberHeaderClickListenerImpl @Inject constructor(
    private val activity: FragmentActivity
) : PartyDetailFragment.OnPartyMemberHeaderClickListener {
    override fun onPartyMemberHeaderClick(item: Any) {
        activity.findNavController(R.id.fcv_main_nav_host).navigate(R.id.action_partyDetailFragment_to_partyMemberFragment)
    }
}