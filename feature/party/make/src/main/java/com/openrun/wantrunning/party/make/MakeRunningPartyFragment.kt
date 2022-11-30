package com.openrun.wantrunning.party.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.openrun.wantrunning.base.ui.compose.WantRunningTheme
import com.openrun.wantrunning.party.make.databinding.FragmentMakeRunningPartyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakeRunningPartyFragment : Fragment() {

    private var _binding: FragmentMakeRunningPartyBinding? = null
    private val binding: FragmentMakeRunningPartyBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMakeRunningPartyBinding.inflate(inflater, container, false)
        binding.composeMakeRunningParty.setContent {
            WantRunningTheme {
                MakeRunningPartyScreen()
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbMakeRunningParty.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}
