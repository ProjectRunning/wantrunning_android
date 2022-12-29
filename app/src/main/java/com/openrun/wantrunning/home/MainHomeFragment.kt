package com.openrun.wantrunning.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentMainHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeFragment : Fragment() {

    private var _binding: FragmentMainHomeBinding? = null
    private val binding: FragmentMainHomeBinding get() = _binding!!

    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_home, container, false)
        binding.ablHome.outlineProvider = null
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        demoCollectionAdapter = DemoCollectionAdapter(this)
        viewPager = binding.vpFragment
        viewPager.adapter = demoCollectionAdapter

        TabLayoutMediator(binding.tlMain, viewPager) { tab, position ->
            run {
                if (position == 0) {
                    tab.text = "전체"
                } else {
                    tab.text = "참여 중"
                }
            }
        }.attach()

        binding.tvSearch.setOnClickListener {
            findNavController().navigate(R.id.action_mainHomeFragment_to_partyPlaceSearchFragment)
        }

        binding.fabHomeMakeRunningParty.setOnClickListener {
            findNavController().navigate(R.id.action_mainHomeFragment_to_makePartyEssentialFragment)
        }
    }
}