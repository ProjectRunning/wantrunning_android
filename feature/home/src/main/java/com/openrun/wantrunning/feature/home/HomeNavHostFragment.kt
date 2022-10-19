package com.openrun.wantrunning.feature.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.feature.home.databinding.FragmentHomeNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeNavHostFragment : Fragment() {

    private var _binding: FragmentHomeNavHostBinding? = null
    private val binding: FragmentHomeNavHostBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeNavHostBinding.inflate(inflater)
        return binding.root
    }
}