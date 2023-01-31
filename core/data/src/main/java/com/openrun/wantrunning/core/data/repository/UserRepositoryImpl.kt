package com.openrun.wantrunning.core.data.repository

import android.util.Log
import com.openrun.wantrunning.core.model.SocialSigningHost
import com.openrun.wantrunning.core.network.model.SocialSigningRequest
import com.openrun.wantrunning.core.network.service.UserService
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getSocialSigningInfo(accessToken: String, host: SocialSigningHost): Boolean {
        return try {
            val request = SocialSigningRequest(accessToken = accessToken, host = host)
            val response = userService.getSocialSigningInfo(request = request)
            Log.d("UserRepositoryImpl", "getSocialSigningInfo: $response")
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}