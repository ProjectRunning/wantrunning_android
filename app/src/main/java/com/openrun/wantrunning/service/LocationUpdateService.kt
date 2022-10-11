package com.openrun.wantrunning.service

import android.R
import android.annotation.SuppressLint
import android.app.*
import android.content.Intent
import android.location.Location
import android.os.*
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.*
import com.openrun.wantrunning.MainActivity


/**
 * Starts location updates on background and publish LocationUpdateEvent upon
 * each new location result.
 */
class LocationUpdateService : Service() {
    private val binder = LocalBinder()
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var locationRequest: LocationRequest? = null
    private val locationSettingsRequest: LocationSettingsRequest? = null

    inner class LocalBinder : Binder() {
        fun getService(): LocationUpdateService = this@LocationUpdateService
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    //endregion
    //onCreate
    override fun onCreate() {
        super.onCreate()
        initData()
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

            sendMessageToActivity(currentLocation!!, "updateLocation");
            //Share/Publish Location
        }
    }

    private fun sendMessageToActivity(l: Location, msg: String) {
        val intent = Intent("GPSLocationUpdates")
        // You can also include some extra data.
        intent.putExtra("Status", msg)
        val b = Bundle()
        b.putParcelable("Location", l)
        intent.putExtra("Location", b)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        prepareForegroundNotification()
        return START_STICKY
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates(locationCallback: LocationCallback) {
        mFusedLocationClient!!.requestLocationUpdates(locationRequest!!,
            locationCallback, Looper.myLooper())
    }

    fun stopLocationUpdates(locationCallback: LocationCallback) {
        mFusedLocationClient!!.removeLocationUpdates(locationCallback)
    }

    private fun prepareForegroundNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                "noti_ch1",
                "Location Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java
            )
            manager.createNotificationChannel(serviceChannel)

            val pendingIntent: PendingIntent =
                Intent(this, MainActivity::class.java).let { notificationIntent ->
                    PendingIntent.getActivity(
                        this, 0, notificationIntent,
                        PendingIntent.FLAG_IMMUTABLE
                    )
                }

            val notification: Notification = Notification.Builder(this, "noti_ch1")
                .setContentTitle("notificationTest Title")
                .setContentText("notificationTest Text")
                .setSmallIcon(R.drawable.sym_def_app_icon)
                .setContentIntent(pendingIntent)
                .build()

            // Notification ID cannot be 0.
            startForeground(1, notification)
        }
    }

    private fun initData() {
        locationRequest = LocationRequest.create()
        locationRequest!!.interval = UPDATE_INTERVAL_IN_MILLISECONDS
        locationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mFusedLocationClient =
            LocationServices.getFusedLocationProviderClient(applicationContext)
    }

    companion object {
        //region data
        private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 3000
    }
}