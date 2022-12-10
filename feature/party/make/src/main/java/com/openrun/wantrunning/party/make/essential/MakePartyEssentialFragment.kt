package com.openrun.wantrunning.party.make.essential

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.openrun.wantrunning.base.ui.compose.WantRunningTheme
import com.openrun.wantrunning.party.make.databinding.FragmentMakePartyEssentialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakePartyEssentialFragment : Fragment() {

    private var _binding: FragmentMakePartyEssentialBinding? = null
    private val binding: FragmentMakePartyEssentialBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMakePartyEssentialBinding.inflate(inflater, container, false)
        binding.composeMakePartyEssential.setContent {
            WantRunningTheme {
                MakePartyEssentialScreen()
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
}
