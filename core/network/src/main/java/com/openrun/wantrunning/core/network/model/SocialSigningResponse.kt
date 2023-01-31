package com.openrun.wantrunning.core.network.model

import com.google.gson.annotations.SerializedName
import com.openrun.wantrunning.core.model.SocialSigningHost

data class SocialSigningResponse(
    val email: String,
    val name: String,
    val host: SocialSigningHost,

    @SerializedName("alreadySigned")
    val isSigned: Boolean
)
