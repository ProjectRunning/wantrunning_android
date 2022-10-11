package com.openrun.wantrunning.feature.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.feature.history.databinding.FragmentHistoryNavHostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryNavHostFragment : Fragment() {

    private var _binding: FragmentHistoryNavHostBinding? = null
    private val binding: FragmentHistoryNavHostBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHistoryNavHostBinding.inflate(inflater)
        return binding.root
    }
}