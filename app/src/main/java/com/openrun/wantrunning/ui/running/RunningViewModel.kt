package com.openrun.wantrunning.ui.running

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//@HiltViewModel
class RunningViewModel : ViewModel(){
    val isRunning = MutableLiveData(false)

    fun startRunning() {
        isRunning.postValue(true)
    }

    fun stopRunning() {
        isRunning.postValue(false)
    }
}