package com.openrun.wantrunning.feature.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.feature.running.databinding.FragmentRunningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RunningFragment : Fragment() {

    private var _binding: FragmentRunningBinding? = null
    private val binding: FragmentRunningBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentRunningBinding.inflate(inflater)
        return binding.root
    }
}