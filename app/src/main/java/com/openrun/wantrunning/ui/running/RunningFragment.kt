package com.openrun.wantrunning.ui.running

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.location.Location
import android.os.Bundle
import android.os.IBinder
import android.util.Log
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
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentRunningBinding
import com.openrun.wantrunning.service.LocationUpdateService


class RunningFragment : Fragment() {
    private lateinit var mService: LocationUpdateService
    private val viewModel by viewModels<RunningViewModel>()
    private  var mBound: Boolean = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as LocationUpdateService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    //Location Callback
    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val currentLocation: Location? = locationResult.lastLocation
            Log.d(
                "Locations",
                currentLocation?.latitude.toString() + "," + currentLocation?.longitude
            )
            //Share/Publish Location
        }
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

        viewModel.isRunning.observe(this.viewLifecycleOwner, Observer{
            if(it) {
                binding.btnStopRun.visibility = View.VISIBLE
                binding.btnStartRun.visibility = View.GONE
            } else {
                binding.btnStopRun.visibility = View.GONE
                binding.btnStartRun.visibility = View.VISIBLE
            }
        } )

        binding.btnStartRun.setOnClickListener {
            viewModel.isRunning.postValue(true)
            mService.startLocationUpdates(locationCallback)
        }

        binding.btnStopRun.setOnClickListener {
            viewModel.isRunning.postValue(false)
            mService.stopLocationUpdates(locationCallback)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        if (supportActionBar != null) supportActionBar.hide()

        Intent(activity, LocationUpdateService::class.java).also {
            intent -> activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
        activity?.unbindService(connection)
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