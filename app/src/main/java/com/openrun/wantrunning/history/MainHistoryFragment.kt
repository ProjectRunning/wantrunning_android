package com.openrun.wantrunning.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.databinding.FragmentMainHistoryBinding
import com.openrun.wantrunning.ui.*
import com.openrun.wantrunning.util.DateTimeUtils
import com.openrun.wantrunning.util.DateTimeUtils.format
import com.openrun.wantrunning.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class MainHistoryFragment : BaseFragment() {

    private var _binding: FragmentMainHistoryBinding? = null
    private val binding: FragmentMainHistoryBinding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMainHistoryBinding.inflate(inflater, container, false)
        binding.root.setContent {
            WantRunningTheme {
                MainHistoryScreen()
            }
        }
        return binding.root
    }
}

@Composable
private fun MainHistoryScreen() {
    val currentLocalDensity = LocalDensity.current
    var topCalendarSheetHeight: Dp by remember { mutableStateOf(value = 0.dp) }

    Box(modifier = Modifier.fillMaxSize()) {
        MainHistoryCalendarSheet(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.TopCenter)
                .border(
                    brush = Brush.verticalGradient(colors = listOf(Color.White, Black_Opacity10)),
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
                    width = 2.dp
                )
                .onSizeChanged {
                    // save calculated top sheet height
                    topCalendarSheetHeight = with(currentLocalDensity) { it.height.toDp() }
                    Log.d("MainHistoryFragment", "MainHistoryScreen: $topCalendarSheetHeight")
                }
        )

        Column(modifier = Modifier.padding(top = topCalendarSheetHeight + 30.dp)) {
            MainHistorySelected(selectedDate = LocalDate.now())
        }
    }
}

@Composable
private fun MainHistoryCalendarSheet(modifier: Modifier = Modifier) {
    // TODO: expand, shrink animations
    // TODO: calendar sheet > content fixed
    Column(modifier = modifier) {
        Text(text = "calendar sheet field", textAlign = TextAlign.Center, modifier = Modifier.padding(all = 24.dp))
    }
}

@Composable
private fun MainHistorySelected(selectedDate: LocalDate) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(), horizontalAlignment = Alignment.CenterHorizontally) {
        val formattedDate = selectedDate.format(pattern = "MM월 dd일")
        val fullDateString = "$formattedDate ${DateTimeUtils.getDayOfWeekForKR(selectedDate.dayOfWeek)}"
        Text(text = fullDateString, fontSize = 16.sp, fontWeight = FontWeight(weight = 600))

        Spacer(modifier = Modifier.size(size = 12.dp))

        // TODO: running history icon
        Image(
            painter = painterResource(id = R.drawable.img_running_done),
            contentDescription = null,
            modifier = Modifier.size(size = 150.dp)
        )

        Spacer(modifier = Modifier.size(size = 12.dp))

        // TODO: running history text
        Text(text = "오늘도 열심히 달리셨네요.", color = Gray40, fontSize = 13.sp)

        // TODO: running history record
        MainHistorySelectedRecordBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 16.dp, end = 16.dp)
        )
    }
}

@Composable
private fun MainHistorySelectedRecordBox(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(color = Gray1_Opacity60, shape = RoundedCornerShape(size = 8.dp))
                .padding(vertical = 20.dp, horizontal = 10.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Text(text = "0:53:25", color = Gray90, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.size(size = 12.dp))

                Text(text = "시간", color = Gray40, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }

            Divider(
                modifier = Modifier
                    .size(width = 1.dp, height = 20.dp)
                    .padding(top = 2.dp), color = Gray10
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Text(text = "10 km", color = Gray90, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.size(size = 12.dp))

                Text(text = "거리", color = Gray40, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }

            Divider(
                modifier = Modifier
                    .size(width = 1.dp, height = 20.dp)
                    .padding(top = 2.dp), color = Gray10
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Text(text = "180 bpm", color = Gray90, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.size(size = 12.dp))

                Text(text = "심박수", color = Gray40, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL, heightDp = 1200)
@Composable
private fun MainHistoryScreenPreview() {
    WantRunningTheme {
        MainHistoryScreen()
    }
}
