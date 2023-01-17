package com.openrun.wantrunning.signing

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import com.openrun.wantrunning.databinding.FragmentSignInBinding
import com.openrun.wantrunning.ui.BasicButton
import com.openrun.wantrunning.ui.WantRunningTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.root.setContent {
            SignInScreen(
                onKakaoSignInButtonClick = this::onKakaoSignInButtonClick,
                onGoogleSignInButtonClick = this::onGoogleSignInButtonClick,
                modifier = Modifier.fillMaxSize()
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

    private fun onGoogleSignInButtonClick() {

    }

    private fun onKakaoTalkSignInAvailable(context: Context) {
        UserApiClient.instance.loginWithKakaoTalk(context) { token, error ->
            if (error != null) {
                // kakao talk sign in failed
                Log.e("SignInFragment", "onKakaoTalkSignInAvailable: ${error.message}", error)

                // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                    return@loginWithKakaoTalk
                }

                // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                UserApiClient.instance.loginWithKakaoAccount(context, callback = this::onRequireKakaoAccountSignIn)
            } else if (token != null) {
                // success to sign in
                Log.d("SignInFragment", "onKakaoTalkSignInAvailable: ${token.accessToken}")
            }
        }
    }

    private fun onRequireKakaoAccountSignIn(oAuthToken: OAuthToken?, throwable: Throwable?) {
        if (throwable != null) {
            Log.e("SignInFragment", "onRequireKakaoAccountSignIn: ${throwable.message}", throwable)
        } else if (oAuthToken != null) {
            Log.d("SignInFragment", "onRequireKakaoAccountSignIn: ${oAuthToken.accessToken}")
        }
    }
}

@Composable
private fun SignInScreen(
    modifier: Modifier = Modifier,
    onKakaoSignInButtonClick: (context: Context) -> Unit,
    onGoogleSignInButtonClick: () -> Unit
) {
    val context: Context = LocalContext.current

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 24.dp)) {
        BasicButton(
            text = "카카오 로그인",
            onClick = { onKakaoSignInButtonClick.invoke(context) },
            modifier = Modifier.fillMaxWidth()
        )

        BasicButton(text = "구글 로그인", onClick = onGoogleSignInButtonClick, modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL)
@Composable
private fun SignInScreenPreview() {
    WantRunningTheme {
        SignInScreen(onKakaoSignInButtonClick = {}, onGoogleSignInButtonClick = {}, modifier = Modifier.fillMaxSize())
    }
}
