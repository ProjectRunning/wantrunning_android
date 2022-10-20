package com.openrun.wantrunning.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.openrun.wantrunning.feature.home.databinding.FragmentListBinding

class ListFragment: Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list, container, false)

        val adapter = PartyListAdapter()

        binding.rvList.adapter = adapter

        adapter.data = listOf(0, 1, 2, 3, 4, 5, 6, 7);

        binding.rvList.layoutManager = LinearLayoutManager(context)

        Log.d("kjy", "ListFragment")
        return binding.root
    }

}