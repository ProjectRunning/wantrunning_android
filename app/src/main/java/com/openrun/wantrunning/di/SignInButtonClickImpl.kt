package com.openrun.wantrunning.di

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.user.UserApiClient
import com.openrun.wantrunning.R
import com.openrun.wantrunning.feature.landing.signin.SignInButtonClick
import javax.inject.Inject

class SignInButtonClickImpl @Inject constructor(
    private val activity: FragmentActivity
) : SignInButtonClick {

    override fun kakaoSignIn() {
//        activity.signInWithKakao()
        activity.findNavController(R.id.fcv_main_nav_host)
            .navigate(R.id.action_landingNavHostFragment_to_mainFeatureFragment)
    }

    override fun googleSignIn(activityResultLauncher: ActivityResultLauncher<Intent>) {
        activity.signInWithGoogle(launcher = activityResultLauncher)
    }

    override fun onGoogleSignInResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == Activity.RESULT_OK) {
            Log.d("SignInButtonClickImpl", "onGoogleSignInResult: ${activityResult.data?.extras}")
        } else {
            Log.d("SignInButtonClickImpl", "onGoogleSignInResult: ${activityResult.data}")
        }
    }

    private fun Activity.signInWithKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            signInWithKakaoTalk()
        } else {
            signInWithKakaoAccount()
        }
    }

    private fun Activity.signInWithKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
            Log.d("SignInFragment", "signInWithKakaoTalk: $token, $error")
        }
    }

    private fun Activity.signInWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(this) { token, error ->
            Log.d("SignInFragment", "signInWithKakaoAccount: $token, $error")
        }
    }

    private fun Activity.signInWithGoogle(launcher: ActivityResultLauncher<Intent>) {
        val googleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)
        launcher.launch(googleSignInClient.signInIntent)
    }
}