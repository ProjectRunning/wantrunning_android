package com.openrun.wantrunning.feature.landing.signin

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.kakao.sdk.user.UserApiClient
import com.openrun.wantrunning.feature.landing.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding get() = _binding!!

    @Inject
    lateinit var signInButtonClick: SignInButtonClick

    private val googleSignInRequestLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            Log.d("SignInFragment", "google sign in complete: ${activityResult.data?.extras}")
        } else {
            Log.d("SignInFragment", "google sign in failed: ${activityResult.data}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignInBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignInKakao.setOnClickListener {
            signInButtonClick.kakaoSignIn()
        }

        binding.btnSignInGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithKakao() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            signInWithKakaoTalk()
        } else {
            signInWithKakaoAccount()
        }
    }

    private fun signInWithKakaoTalk() {
        UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
            Log.d("SignInFragment", "signInWithKakaoTalk: $token, $error")
        }
    }

    private fun signInWithKakaoAccount() {
        UserApiClient.instance.loginWithKakaoAccount(requireContext()) { token, error ->
            Log.d("SignInFragment", "signInWithKakaoAccount: $token, $error")
        }
    }

    private fun signInWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions)
        googleSignInRequestLauncher.launch(googleSignInClient.signInIntent)
    }
}