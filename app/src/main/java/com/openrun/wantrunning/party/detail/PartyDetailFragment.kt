package com.openrun.wantrunning.party.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentPartyDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartyDetailFragment : Fragment() {

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
//        val menuHost: MenuHost = requireActivity()
//
//        menuHost.addMenuProvider(object : MenuProvider {
//            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
//                // Add menu items here
//                menuInflater.inflate(R.menu.menu_toolbar, menu)
//            }
//
//            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
//                // Handle the menu selection
//                return true
//            }
//        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

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

        val adapter = PartyMemberAdapter()

        binding.rvPartyMember.adapter = adapter

        adapter.data = listOf(0, 1, 2, 3, 4, 5, 6, 7)

        binding.rvPartyMember.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        binding.llPartyMemberHeader.setOnClickListener {
            // TODO: navigate to detail
        }

        super.onViewCreated(view, savedInstanceState)
    }
}