package com.openrun.wantrunning.signing

import androidx.lifecycle.ViewModel
import com.openrun.wantrunning.core.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {


}