package com.openrun.wantrunning.party.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentPartyMemberBinding

class PartyMemberFragment : Fragment() {

    private var _binding: FragmentPartyMemberBinding? = null
    private val binding: FragmentPartyMemberBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_member, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAdapter()

        binding.toolbar.setNavigationOnClickListener {
            activity?.findNavController(R.id.fcv_main_nav_host)?.popBackStack()
        }

        binding.rvPartyMember.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initAdapter() {
        val adapter = VerticalPartyMemberAdapter()

        binding.rvPartyMember.adapter = adapter

        adapter.data = listOf(0, 1, 2, 3, 4, 5, 6, 7)
    }
}