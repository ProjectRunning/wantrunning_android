package com.openrun.wantrunning.counting

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentCountingBinding
import com.openrun.wantrunning.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountingFragment : BaseFragment() {

    override var fullScreen: Boolean = true

    private var _binding: FragmentCountingBinding? = null
    private val binding: FragmentCountingBinding get() = _binding!!
    private val timeCountInMilliSeconds: Long = 1000 * 3

    private val countDownTimer = object : CountDownTimer(timeCountInMilliSeconds, 50) {
        override fun onTick(p0: Long) {
            val currentCount = ((p0/1000)+1).toInt()
            val progress =  ((timeCountInMilliSeconds - p0) % 1000).toInt()
            binding.tvCount.text = currentCount.toString()
            binding.progressBar.progress = progress
        }

        override fun onFinish() {
            findNavController().popBackStack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_counting, container, false)

        binding.progressBar.max = 1000
        binding.progressBar.progress = 0

        countDownTimer.start()

        return binding.root
    }

}