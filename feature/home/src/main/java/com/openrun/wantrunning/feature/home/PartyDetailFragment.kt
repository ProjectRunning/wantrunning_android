package com.openrun.wantrunning.feature.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.openrun.wantrunning.feature.home.databinding.FragmentPartyDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PartyDetailFragment : Fragment() {

    private var _binding: FragmentPartyDetailBinding? = null
    private val binding: FragmentPartyDetailBinding get() = _binding!!

    @Inject
    lateinit var partyMemberHeaderClickListener: OnPartyMemberHeaderClickListener

    interface OnPartyMemberHeaderClickListener {
        fun onPartyMemberHeaderClick(item: Any)
    }

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

        binding.toolbar.inflateMenu(R.menu.menu_toolbar)

        binding.toolbar.setOnMenuItemClickListener(Toolbar.OnMenuItemClickListener {
            return@OnMenuItemClickListener when(it.itemId) {
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
            partyMemberHeaderClickListener.onPartyMemberHeaderClick(0)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PartyDetailFragment()
    }
}