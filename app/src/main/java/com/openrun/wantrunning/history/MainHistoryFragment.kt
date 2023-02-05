package com.openrun.wantrunning.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.databinding.FragmentMainHistoryBinding
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainHistoryFragment : BaseFragment() {

    private var _binding: FragmentMainHistoryBinding? = null
    private val binding: FragmentMainHistoryBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainHistoryBinding.inflate(inflater, container, false)
        binding.root.setContent {
            WantRunningTheme {

            }
        }
        return binding.root
    }
}

@Composable
private fun MainHistoryScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        MainHistoryCalendarSheet()
    }
}

@Composable
private fun MainHistoryCalendarSheet() {
    // TODO: calendar sheet > height unknown
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp))
            .fillMaxWidth()
            .height(height = 128.dp)
    ) {
        Text(text = "calendar sheet field", textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL, heightDp = 1200)
@Composable
private fun MainHistoryScreenPreview() {
    WantRunningTheme {
        MainHistoryScreen()
    }
}
