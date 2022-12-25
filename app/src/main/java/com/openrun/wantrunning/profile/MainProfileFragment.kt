package com.openrun.wantrunning.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.openrun.wantrunning.databinding.FragmentMainProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainProfileFragment : Fragment() {

    private var _binding: FragmentMainProfileBinding? = null
    private val binding: FragmentMainProfileBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainProfileBinding.inflate(inflater, container, false)
        return binding.root
    }
}