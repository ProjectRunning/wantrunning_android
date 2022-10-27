package com.openrun.wantrunning.feature.landing.signin

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
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
        signInButtonClick.onGoogleSignInResult(activityResult)
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
            signInButtonClick.googleSignIn(activityResultLauncher = googleSignInRequestLauncher)
        }
    }
}