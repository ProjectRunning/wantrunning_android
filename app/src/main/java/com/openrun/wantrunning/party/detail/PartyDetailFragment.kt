package com.openrun.wantrunning.party.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentPartyDetailBinding
import com.openrun.wantrunning.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartyDetailFragment : BaseFragment() {

    private var _binding: FragmentPartyDetailBinding? = null
    private val binding: FragmentPartyDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_party_detail, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initToolBar()
        initAdapter()

        binding.rvPartyMember.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.llPartyMemberHeader.setOnClickListener {
            activity?.findNavController(R.id.fcv_main_nav_host)?.navigate(R.id.action_partyDetailFragment_to_partyMemberFragment)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initToolBar() {
        binding.toolbar.inflateMenu(R.menu.menu_party_detail_toolbar)

        binding.toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
            return@OnMenuItemClickListener when (it.itemId) {
                R.id.action_home -> {
                    true
                }
                R.id.action_share -> {
                    true
                }
                else -> false
            }
        })

        binding.toolbar.setNavigationOnClickListener {
            activity?.findNavController(R.id.fcv_main_nav_host)?.popBackStack()
        }
    }

    private fun initAdapter() {
        val adapter = PartyMemberAdapter()

        binding.rvPartyMember.adapter = adapter

        adapter.data = listOf(0, 1, 2, 3, 4, 5, 6, 7)
    }
}