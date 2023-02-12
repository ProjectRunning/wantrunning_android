package com.openrun.wantrunning.core.network.model

import com.google.gson.annotations.SerializedName
import com.openrun.wantrunning.core.model.SocialSigningHost

data class SocialSigningRequest(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("host") val host: SocialSigningHost
)
