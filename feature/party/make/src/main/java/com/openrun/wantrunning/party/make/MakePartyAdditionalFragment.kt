package com.openrun.wantrunning.party.make

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.openrun.wantrunning.party.make.databinding.FragmentMakePartyAdditiionalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MakePartyAdditionalFragment : Fragment() {

    private var _binding: FragmentMakePartyAdditiionalBinding? = null
    private val binding: FragmentMakePartyAdditiionalBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMakePartyAdditiionalBinding.inflate(inflater, container, false)
        binding.composeMakePartyAdditional
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tbMakePartyAdditional.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }
}