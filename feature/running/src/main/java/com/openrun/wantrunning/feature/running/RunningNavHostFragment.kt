package com.openrun.wantrunning.feature.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.feature.running.databinding.FragmentRunningNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunningNavHostFragment : Fragment() {

    private var _binding: FragmentRunningNavHostBinding? = null
    private val binding: FragmentRunningNavHostBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRunningNavHostBinding.inflate(inflater)
        return binding.root
    }
}