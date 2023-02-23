package com.openrun.wantrunning.signing

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.openrun.wantrunning.BuildConfig
import com.openrun.wantrunning.R
import com.openrun.wantrunning.core.model.SocialSigningHost
import com.openrun.wantrunning.databinding.FragmentSignInBinding
import com.openrun.wantrunning.ui.*
import com.openrun.wantrunning.ui.component.BaseButton
import com.openrun.wantrunning.util.autoCleared
import com.openrun.wantrunning.util.base.BaseFragment
import com.openrun.wantrunning.util.extension.makeToast

class SignInFragment : BaseFragment() {

    private var binding by autoCleared<FragmentSignInBinding>()

    private val viewModel by viewModels<SignInViewModel>()

    private val googleSignInActivityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            onGoogleSignInResult(activityResult = activityResult)
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.root.setContent {
            SignInScreen(
                onKakaoSignInButtonClick = this::onKakaoSignInButtonClick,
                onGoogleSignInButtonClick = this::onGoogleSignInButtonClick
            )
        }
        return binding.root
    }

    private fun onKakaoSignInButtonClick(context: Context) {
        // check kakao talk installation
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context = context)) {
            onKakaoTalkSignInAvailable(context = context)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context = context, callback = this::onRequireKakaoAccountSignIn)
        }
    }

    private fun onGoogleSignInButtonClick(context: Context) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(BuildConfig.GOOGLE_WEB_CLIENT_ID)
            .build()
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        googleSignInActivityResultLauncher.launch(googleSignInClient.signInIntent)
    }

    private fun onKakaoTalkSignInAvailable(context: Context) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                // TODO: kakao talk sign in failed
                Log.e("SignInFragment", "onKakaoTalkSignInAvailable: ${error.message}", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                UserApiClient.instance.loginWithKakaoAccount(context, callback = this::onRequireKakaoAccountSignIn)
            } else if (token != null) {
                viewModel.getSocialSigningInfo(accessToken = token.accessToken, host = SocialSigningHost.Kakao)
            }
        }
    }

    private fun onRequireKakaoAccountSignIn(oAuthToken: OAuthToken?, throwable: Throwable?) {
        if (throwable != null) {
            // TODO: error control
            Log.e("SignInFragment", "onRequireKakaoAccountSignIn: ${throwable.message}", throwable)
        } else if (oAuthToken != null) {
            viewModel.getSocialSigningInfo(accessToken = oAuthToken.accessToken, host = SocialSigningHost.Kakao)
        }
    }

    private fun onGoogleSignInResult(activityResult: ActivityResult) {
        if (activityResult.resultCode == Activity.RESULT_OK) {
            val completedTask = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
            onGoogleSignInComplete(task = completedTask)
        } else {
            makeToast("구글 로그인을 완료하지 못했습니다.", Toast.LENGTH_SHORT)
        }
    }

    private fun onGoogleSignInComplete(task: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = task.getResult(ApiException::class.java)
            viewModel.getSocialSigningInfo(accessToken = account.idToken!!, host = SocialSigningHost.Google)
        } catch (e: ApiException) {
            // TODO: error control
            Log.e("SignInFragment", "onGoogleSignInComplete: ${e.statusCode}", e)
        } catch (e: NullPointerException) {
            // TODO: cannot fetch google oauth id token
            Log.e("SignInFragment", "onGoogleSignInComplete: ${e.message}", e)
        }
    }
}

@Composable
private fun SignInScreen(
    onKakaoSignInButtonClick: (context: Context) -> Unit,
    onGoogleSignInButtonClick: (context: Context) -> Unit
) {
    val context: Context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "함께 달리는 즐거움", style = WantRunningTypography.h2.copy(fontSize = 18.sp, color = Gray60))

        Spacer(modifier = Modifier.size(size = 8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_lightning_24),
                contentDescription = null,
                modifier = Modifier.size(size = 48.dp)
            )

            Text(text = "워너런", fontSize = 30.sp, fontWeight = FontWeight(weight = 700), color = Primary2)
        }

        Spacer(modifier = Modifier.size(size = 75.dp))

        BaseButton(
            onClick = { onKakaoSignInButtonClick.invoke(context) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = ContainerKakao),
            leadingIcon = painterResource(id = R.drawable.ic_kakao_ci_20),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(
                text = "카카오로 로그인 하기",
                style = WantRunningTypography.button.copy(
                    color = LabelKakao,
                    fontWeight = FontWeight(weight = 500),
                    fontSize = 14.sp
                )
            )
        }

        Spacer(modifier = Modifier.size(size = 12.dp))

        BaseButton(
            onClick = { onGoogleSignInButtonClick.invoke(context) },
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = ContainerGoogle),
            border = BorderStroke(width = 1.dp, color = Gray10),
            leadingIcon = painterResource(id = R.drawable.ic_google_ci_20),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
        ) {
            Text(
                text = "구글로 로그인 하기",
                style = WantRunningTypography.button.copy(
                    color = LabelGoogle,
                    fontWeight = FontWeight(weight = 500),
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL)
@Composable
private fun SignInScreenPreview() {
    WantRunningTheme {
        SignInScreen(onKakaoSignInButtonClick = {}, onGoogleSignInButtonClick = {})
    }
}
