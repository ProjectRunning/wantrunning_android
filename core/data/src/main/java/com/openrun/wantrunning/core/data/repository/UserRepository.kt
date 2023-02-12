package com.openrun.wantrunning.core.data.repository

import com.openrun.wantrunning.core.model.SocialSigningHost

interface UserRepository {

    suspend fun getSocialSigningInfo(accessToken: String, host: SocialSigningHost): Boolean
}