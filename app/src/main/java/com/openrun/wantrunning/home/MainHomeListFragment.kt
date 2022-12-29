package com.openrun.wantrunning.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentMainHomeListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHomeListFragment : Fragment() {

    private var _binding: FragmentMainHomeListBinding? = null
    private val binding: FragmentMainHomeListBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main_home_list, container, false)

        val adapter = MainHomePartyListAdapter()
        adapter.setOnPartyListItemClickListener(
            object : MainHomePartyListAdapter.OnPartyListItemClickListener {
                override fun onPartyListItemClick(item: Any) {
                    activity?.findNavController(R.id.fcv_main_nav_host)?.navigate(R.id.action_mainHomeFragment_to_partyDetailFragment)
                }
            }
        )
        binding.rvMainHomeList.adapter = adapter
        adapter.data = listOf(0, 1, 2, 3, 4, 5, 6, 7)

        return binding.root
    }
}