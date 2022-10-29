package com.openrun.wantrunning

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WantRunningApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, resources.getString(R.string.kakao_native_app_key))
    }
}