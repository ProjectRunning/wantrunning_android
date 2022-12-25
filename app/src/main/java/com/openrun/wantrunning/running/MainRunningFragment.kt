package com.openrun.wantrunning.running

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.openrun.wantrunning.databinding.FragmentMainRunningBinding
import com.openrun.wantrunning.running.service.LocationUpdateService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainRunningFragment : Fragment(), OnMapReadyCallback {
    private lateinit var mMapView: MapView
    private lateinit var mService: LocationUpdateService
    private val viewModel by viewModels<MainRunningViewModel>()
    private var mBound: Boolean = false

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(className: ComponentName?, service: IBinder?) {
            val binder = service as LocationUpdateService.LocalBinder
            mService = binder.getService()
            mService.startLocationUpdates(viewModel.currentLocationOverlayCallback)
            mBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMapView.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState)

        val binding: FragmentMainRunningBinding =
            DataBindingUtil.inflate(inflater, com.openrun.wantrunning.R.layout.fragment_main_running, container, false)

        mMapView = binding.mapView
        mMapView.getMapAsync(this)

        binding.vm = viewModel

        binding.btnStartRun.setOnClickListener {
            viewModel.isRunning.postValue(true)
        }

        binding.btnStopRun.setOnClickListener {
            viewModel.isRunning.postValue(false)
        }

        viewModel.isRunning.observe(this.viewLifecycleOwner) {
            if (it) {
                binding.btnStopRun.visibility = View.VISIBLE
                binding.btnStartRun.visibility = View.GONE
            } else {
                binding.btnStopRun.visibility = View.GONE
                binding.btnStartRun.visibility = View.VISIBLE
            }
        }

        val locationPermissionRequest = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                    // Precise location access granted.
                }
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                    // Only approximate location access granted.
                }
                else -> {
                    // No location access granted.
                }
            }
        }

        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        val supportActionBar: ActionBar? = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.hide()

        Intent(activity, LocationUpdateService::class.java).also { intent ->
            activity?.bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar
        supportActionBar?.show()
        activity?.unbindService(connection)
        mMapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

    override fun onDestroy() {
        mService.stopLocationUpdates(viewModel.currentLocationOverlayCallback)
        super.onDestroy()
    }

    override fun onMapReady(naverMap: NaverMap) {
        viewModel.setNaverMap(naverMap)
    }
}