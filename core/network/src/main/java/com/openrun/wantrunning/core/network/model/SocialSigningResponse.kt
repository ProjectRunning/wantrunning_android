package com.openrun.wantrunning.core.network.model

import com.google.gson.annotations.SerializedName
import com.openrun.wantrunning.core.model.SocialSigningHost

data class SocialSigningResponse(
    @SerializedName("email")
    val email: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("host")
    val host: SocialSigningHost,

    @SerializedName("alreadySigned")
    val isSigned: Boolean,

    @SerializedName("jwtToken")
    val accessToken: String,

    @SerializedName("refreshToken")
    val refreshToken: String
)
