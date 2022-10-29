package com.openrun.wantrunning.feature.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.feature.landing.databinding.FragmentLandingNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LandingNavHostFragment : Fragment() {

    private var _binding: FragmentLandingNavHostBinding? = null
    private val binding: FragmentLandingNavHostBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLandingNavHostBinding.inflate(inflater)
        return binding.root
    }
}