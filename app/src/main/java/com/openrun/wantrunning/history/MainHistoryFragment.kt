package com.openrun.wantrunning.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.databinding.FragmentMainHistoryBinding
import com.openrun.wantrunning.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHistoryFragment : BaseFragment() {

    private var _binding: FragmentMainHistoryBinding? = null
    private val binding: FragmentMainHistoryBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }
}