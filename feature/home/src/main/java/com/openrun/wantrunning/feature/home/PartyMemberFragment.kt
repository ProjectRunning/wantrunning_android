package com.openrun.wantrunning.feature.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.openrun.wantrunning.feature.home.databinding.FragmentPartyMemberBinding

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
        val adapter = VerticalPartyMemberAdapter()

        binding.rvPartyMember.adapter = adapter

        adapter.data = listOf(0, 1, 2, 3, 4, 5, 6, 7)

        binding.rvPartyMember.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PartyMemberFragment()
    }
}