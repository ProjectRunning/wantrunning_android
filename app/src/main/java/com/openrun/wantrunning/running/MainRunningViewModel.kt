package com.openrun.wantrunning.running

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainRunningViewModel @Inject constructor() : ViewModel() {
    val isRunning = MutableLiveData(false)
    private lateinit var mNaverMap: NaverMap

    fun setNaverMap (naverMap: NaverMap) {
        mNaverMap = naverMap
    }

    //Callback for Current Location Overlay
    val currentLocationOverlayCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            super.onLocationResult(locationResult)
            val currentLocation: Location? = locationResult.lastLocation

            if(currentLocation != null) {
                val currentLatLng = LatLng(currentLocation.latitude, currentLocation.longitude)
                val cameraUpdate = CameraUpdate.scrollTo(currentLatLng)

                mNaverMap.moveCamera(cameraUpdate)

                val locationOverlay = mNaverMap.locationOverlay
                if(!locationOverlay.isVisible) {
                    locationOverlay.isVisible = true
                    locationOverlay.position = currentLatLng
                }

                Log.d(
                    "Current Locations",
                    currentLocation.latitude.toString() + "," + currentLocation.longitude
                )
            }
        }
    }
}