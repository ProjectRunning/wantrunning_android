package com.openrun.wantrunning.core.network.service

import com.openrun.wantrunning.core.network.model.SocialSigningRequest
import com.openrun.wantrunning.core.network.model.SocialSigningResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("api/login/oauth")
    suspend fun getSocialSigningInfo(
        @Body request: SocialSigningRequest
    ): SocialSigningResponse
}