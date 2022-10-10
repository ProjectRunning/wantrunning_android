//@AndroidEntryPoint
//class MainActivity : AppCompatActivity() {
//
//    private val viewModel by viewModels<MainViewModel>();
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//
//        binding.lifecycleOwner = this
//        binding.vm = viewModel
//
//        viewModel.fetchUserList()
//    }
//}

package com.openrun.wantrunning

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.transaction
import com.openrun.wantrunning.databinding.ActivityMainBinding
import com.openrun.wantrunning.ui.running.RunningFragment

// Implement OnMapReadyCallback.
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout file as the content view.
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}