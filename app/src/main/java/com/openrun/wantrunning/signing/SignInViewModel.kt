package com.openrun.wantrunning.signing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openrun.wantrunning.core.data.repository.UserRepository
import com.openrun.wantrunning.core.model.SocialSigningHost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    fun getSocialSigningInfo(accessToken: String, host: SocialSigningHost) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.getSocialSigningInfo(accessToken, host)
            Log.d("SignInViewModel", "getSocialSigningInfo: $response")
        }
    }
}