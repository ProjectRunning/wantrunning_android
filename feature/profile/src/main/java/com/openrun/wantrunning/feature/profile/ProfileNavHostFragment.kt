package com.openrun.wantrunning.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.feature.profile.databinding.FragmentProfileNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileNavHostFragment : Fragment() {

    private var _binding: FragmentProfileNavHostBinding? = null
    private val binding: FragmentProfileNavHostBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileNavHostBinding.inflate(inflater)
        return binding.root
    }
}