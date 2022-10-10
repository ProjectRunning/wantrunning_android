package com.openrun.wantrunning.ui.running

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentRunningBinding


class RunningFragment : Fragment() {

    private val viewModel by viewModels<RunningViewModel>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var binding : FragmentRunningBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_running, container, false)

        binding.vm = viewModel

        binding.fabBack.setOnClickListener(
            View.OnClickListener {
                Navigation.findNavController(binding.root).navigate(R.id.action_running_to_home)
            }
        )

//        viewModel.isRunning.observe(this.viewLifecycleOwner, Observer{
//            if(it) {
//                binding.btnStopRun.visibility = View.VISIBLE
//                binding.btnStartRun.visibility = View.GONE
//            } else {
//                binding.btnStopRun.visibility = View.GONE
//                binding.btnStartRun.visibility = View.VISIBLE
//            }
//        } )

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RunningFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            RunningFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}