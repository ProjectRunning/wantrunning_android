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
            val signingRequest = SocialSigningRequest(accessToken = accessToken, host = host)
            val response = userService.getSocialSigningInfo(request = signingRequest)
            Log.d("UserRepositoryImpl", "getSocialSigningInfo: $response")
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("UserRepositoryImpl", "getSocialSigningInfo: ${e.message}", e)
            false
        }
    }
}