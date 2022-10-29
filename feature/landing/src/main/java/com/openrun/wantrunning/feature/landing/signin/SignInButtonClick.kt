package com.openrun.wantrunning.feature.landing.signin

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher

interface SignInButtonClick {

    fun kakaoSignIn()

    fun googleSignIn(activityResultLauncher: ActivityResultLauncher<Intent>)

    fun onGoogleSignInResult(activityResult: ActivityResult)
}