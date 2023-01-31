package com.openrun.wantrunning.party.make.essential

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.databinding.FragmentMakePartyEssentialBinding
import com.openrun.wantrunning.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

class MakePartyEssentialFragment : BaseFragment() {

    private var _binding: FragmentMakePartyEssentialBinding? = null
    private val binding: FragmentMakePartyEssentialBinding get() = _binding!!

    private val viewModel: MakePartyEssentialViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMakePartyEssentialBinding.inflate(inflater, container, false)
        binding.composeMakePartyEssential.setContent {
            WantRunningTheme {
                MakePartyEssentialScreen(
                    onRegionButtonClick = this::onRegionButtonClick
                )
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbMakePartyEssential.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun onRegionButtonClick() {
        findNavController().navigate(R.id.action_global_addressSearchFragment)
    }
}
