package com.openrun.wantrunning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.openrun.wantrunning.databinding.FragmentMainFeatureBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFeatureFragment : Fragment() {

    private var _binding: FragmentMainFeatureBinding? = null
    private val binding: FragmentMainFeatureBinding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainFeatureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initNavController()
    }

    private fun initNavController() {
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.fcv_main_feature_nav_host) as NavHostFragment
        navController = navHostFragment.navController
        binding.bnvMainFeatureNav.setupWithNavController(navController)
    }
}