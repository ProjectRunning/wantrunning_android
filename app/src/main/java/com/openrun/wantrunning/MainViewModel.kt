package com.openrun.wantrunning

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    // TODO: session control - 메인화면으로 바로 가려면 true 로 수정하시면 됩니다.
    var isSessionValid: Boolean = true
        private set
}