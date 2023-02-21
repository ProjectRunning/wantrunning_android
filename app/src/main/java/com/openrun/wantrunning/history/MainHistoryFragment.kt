package com.openrun.wantrunning.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.openrun.wantrunning.core.model.RunningHistory
import com.openrun.wantrunning.databinding.FragmentMainHistoryBinding
import com.openrun.wantrunning.ui.*
import com.openrun.wantrunning.util.DateTimeUtils
import com.openrun.wantrunning.util.DateTimeUtils.format
import com.openrun.wantrunning.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MainHistoryScreen() {
    val currentLocalDensity = LocalDensity.current
    var topCalendarSheetHeight: Dp by remember { mutableStateOf(value = 0.dp) }

    Box(modifier = Modifier.fillMaxSize()) {
        MainHistoryCalendarSheet(
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.TopCenter)
                .onSizeChanged {
                    // save calculated top sheet height
                    topCalendarSheetHeight = with(currentLocalDensity) { it.height.toDp() }
                    Log.d("MainHistoryFragment", "MainHistoryScreen: $topCalendarSheetHeight")
                }
        )

        LazyColumn(modifier = Modifier.padding(top = topCalendarSheetHeight)) {
            // first, setup selected date record
            item {
                // TODO: selected date
                // padding from top calendar sheet with 30dp
                MainHistorySelectedDateRecordContent(
                    selectedDate = LocalDate.now(), modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 30.dp)
                )
            }

            // item for divider
            item {
                Divider(color = Gray1, thickness = 6.dp, modifier = Modifier.padding(top = 24.dp))
            }

            // item for recent records title
            item {
                Text(
                    text = "최근 운동 기록",
                    style = WantRunningTypography.h1.copy(fontWeight = FontWeight(weight = 600)),
                    modifier = Modifier.padding(top = 24.dp, start = 16.dp)
                )
            }

            if (RunningHistory.recentMockingDataList.isEmpty()) {
                // item for empty recent record
                item {
                    MainHistoryRecentRecordsEmpty(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp, horizontal = 16.dp)
                    )
                }
            } else {
                // items for recent records
                RunningHistory.recentMockingDataList.groupBy { it.date }.forEach { (date, runningHistories) ->
                    // header for date
                    item {
                        val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
                        val localDate = LocalDate.parse(date, dateFormatter)
                        val dateString = "${localDate.monthValue}월 " +
                                "${localDate.dayOfMonth}일 " +
                                DateTimeUtils.getDayOfWeekForKR(localDate.dayOfWeek)
                        Text(
                            text = dateString,
                            style = WantRunningTypography.body2.copy(color = Gray40),
                            modifier = Modifier.padding(start = 16.dp, top = 24.dp)
                        )
                    }

                    // records
                    items(items = runningHistories) { item: RunningHistory ->
                        MainHistoryRecentRecordItem(
                            runningHistory = item,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)
                        )
                    }

                    // divider - check last item by date
                    if (RunningHistory.recentMockingDataList.last().date != date) {
                        item {
                            Divider(color = Gray5, modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MainHistoryCalendarSheet(modifier: Modifier = Modifier) {
    // TODO: expand, shrink animations
    Column(
        // TODO: drop shadow
        modifier = modifier.border(
            brush = Brush.verticalGradient(colors = listOf(Color.White, Black_Opacity10)),
            shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp),
            width = 2.dp
        )
    ) {
        Text(text = "calendar sheet field", textAlign = TextAlign.Center, modifier = Modifier.padding(all = 24.dp))
    }
}

@Composable
private fun MainHistorySelectedDateRecordContent(modifier: Modifier = Modifier, selectedDate: LocalDate) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${selectedDate.format(pattern = "MM월 dd일")} " +
                    DateTimeUtils.getDayOfWeekForKR(selectedDate.dayOfWeek),
            style = WantRunningTypography.h1.copy(fontWeight = FontWeight(weight = 600))
        )

        Spacer(modifier = Modifier.size(size = 12.dp))

        // TODO: icon by running count
        Image(
            painter = painterResource(id = R.drawable.img_running_done),
            contentDescription = null,
            modifier = Modifier.size(size = 150.dp)
        )

        Spacer(modifier = Modifier.size(size = 12.dp))

        // TODO: text by running count
        Text(
            text = "오늘도 열심히 달리셨네요!",
            style = WantRunningTypography.body1.copy(color = Gray40, fontWeight = FontWeight(weight = 600))
        )

        // TODO: setup record boxes
        if (RunningHistory.selectedMockingData.isNotEmpty()) {
            Spacer(modifier = Modifier.size(size = 18.dp))

            RunningHistory.selectedMockingData.forEach { runningHistory ->
                MainHistorySelectedDateRecordBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, start = 16.dp, end = 16.dp),
                    time = runningHistory.time,
                    distance = runningHistory.distance,
                    heartbeat = runningHistory.heartbeat
                )
            }
        }
    }
}

@Composable
private fun MainHistorySelectedDateRecordBox(
    modifier: Modifier = Modifier,
    time: String,
    distance: Int,
    heartbeat: Int
) {
    Box(modifier = modifier.background(color = Gray1_Opacity60, shape = RoundedCornerShape(size = 8.dp))) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 12.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Text(text = time, color = Gray90, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.size(size = 12.dp))

                Text(text = "시간", color = Gray40, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }

            Divider(
                modifier = Modifier
                    .size(width = 1.dp, height = 20.dp)
                    .padding(top = 2.dp), color = Gray10
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Text(text = "$distance km", color = Gray90, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.size(size = 12.dp))

                Text(text = "거리", color = Gray40, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }

            Divider(
                modifier = Modifier
                    .size(width = 1.dp, height = 20.dp)
                    .padding(top = 2.dp), color = Gray10
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
                Text(text = "$heartbeat bpm", color = Gray90, fontSize = 18.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.size(size = 12.dp))

                Text(text = "심박수", color = Gray40, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun MainHistoryRecentRecordsEmpty(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.img_running_empty),
            contentDescription = null,
            modifier = Modifier.size(size = 150.dp)
        )

        Text(
            text = "최근에 달린 기록이 없어요.",
            style = WantRunningTypography.body1.copy(color = Gray40)
        )
    }
}

@Composable
private fun MainHistoryRecentRecordItem(modifier: Modifier = Modifier, runningHistory: RunningHistory) {
    Row(modifier = modifier) {
        Image(
            painter = if (runningHistory.isParty) {
                painterResource(id = R.drawable.ic_running_party_40)
            } else {
                painterResource(id = R.drawable.ic_running_solo_40)
            },
            contentDescription = null
        )

        Spacer(modifier = Modifier.size(size = 10.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
            Text(text = runningHistory.time, style = WantRunningTypography.h2.copy(color = Gray90))

            Spacer(modifier = Modifier.size(size = 8.dp))

            Text(text = "시간", style = WantRunningTypography.body1.copy(color = Gray40))
        }

        Spacer(modifier = Modifier.size(size = 4.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
            Text(text = "${runningHistory.distance} km", style = WantRunningTypography.h2.copy(color = Gray90))

            Spacer(modifier = Modifier.size(size = 8.dp))

            Text(text = "거리", style = WantRunningTypography.body1.copy(color = Gray40))
        }

        Spacer(modifier = Modifier.size(size = 4.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
            Text(text = "${runningHistory.heartbeat} bpm", style = WantRunningTypography.h2.copy(color = Gray90))

            Spacer(modifier = Modifier.size(size = 8.dp))

            Text(text = "심박수", style = WantRunningTypography.body1.copy(color = Gray40))
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
