package com.openrun.wantrunning.address

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.databinding.FragmentAddressSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressSearchFragment : Fragment() {

    private var _binding: FragmentAddressSearchBinding? = null
    private val binding: FragmentAddressSearchBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAddressSearchBinding.inflate(inflater, container, false)
        return binding.root
    }
}