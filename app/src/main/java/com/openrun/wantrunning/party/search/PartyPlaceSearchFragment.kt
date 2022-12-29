package com.openrun.wantrunning.party.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentPartyPlaceSearchBinding

class PartyPlaceSearchFragment : Fragment(), PartySearchRecommendAdapter.PartySearchRecommendItemClickListener {

    private var _binding: FragmentPartyPlaceSearchBinding? = null
    private val binding: FragmentPartyPlaceSearchBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_place_search, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()

        binding.toolbar.setNavigationOnClickListener {
            activity?.findNavController(R.id.fcv_main_nav_host)?.popBackStack()
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter() {
        val adapter = PartySearchRecommendAdapter()

        adapter.setPartySearchRecommendItemClickListener(this)

        binding.rvPartySearchRecommend.adapter = adapter

        adapter.setList(listOf(0, 1, 2, 3, 4, 5, 6, 7))
    }

    override fun onPartySearchRecommendItemClick(item: Any) {
        // todo
        ;
    }

    override fun onStarClick(item: Any) {
        // todo
        ;
    }
}