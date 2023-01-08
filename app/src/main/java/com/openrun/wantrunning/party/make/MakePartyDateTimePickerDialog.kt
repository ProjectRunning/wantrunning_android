package com.openrun.wantrunning.party.make

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.ui.BasicButton
import com.openrun.wantrunning.ui.Gray30
import com.openrun.wantrunning.ui.Gray5
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.ui.component.NumberPicker
import com.openrun.wantrunning.util.DateTimePickerUtils
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun MakePartyDateTimePickerDialog(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        MakePartyDateTimeNumberPicker()

        Spacer(modifier = Modifier.size(size = 8.dp))

        Divider(color = Gray5)

        Spacer(modifier = Modifier.size(size = 24.dp))

        Text(
            text = "2023년 01월 08일 일요일",
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(size = 8.dp))

        Text(
            text = "오후 7시 00분",
            style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(size = 24.dp))

        Text(
            text = "설정 가능한 일정이에요.",
            color = Gray30,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(size = 12.dp))

        BasicButton(text = "일정 설정 완료", onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun MakePartyDateTimeNumberPicker(modifier: Modifier = Modifier) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(space = 4.dp)) {
        MakePartyDateTimeYearPicker(modifier = Modifier.weight(1f))

        MakePartyDateTimeMonthPicker(modifier = Modifier.weight(1f))

        MakePartyDateTimeDayOfMonthPicker(modifier = Modifier.weight(1f))

        MakePartyDateTimeMiddayPicker(modifier = Modifier.weight(1f))

        MakePartyDateTimeHourPicker(modifier = Modifier.weight(1f))

        MakePartyDateTimeMinutePicker(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun MakePartyDateTimeYearPicker(modifier: Modifier = Modifier) {
    var selectedYear: Int by remember { mutableStateOf(LocalDate.now().year) }

    NumberPicker(
        values = DateTimePickerUtils.years,
        displayedValues = DateTimePickerUtils.displayedValuesForYears,
        selectedValue = selectedYear,
        onValueSelected = { selectedYear = it },
        modifier = modifier
    )
}

@Composable
private fun MakePartyDateTimeMonthPicker(modifier: Modifier = Modifier) {
    var selectedMonth: Int by remember { mutableStateOf(LocalDate.now().monthValue) }

    NumberPicker(
        values = DateTimePickerUtils.months,
        displayedValues = DateTimePickerUtils.displayedValuesForMonths,
        selectedValue = selectedMonth,
        onValueSelected = { selectedMonth = it },
        modifier = modifier
    )
}

@Composable
private fun MakePartyDateTimeDayOfMonthPicker(modifier: Modifier = Modifier) {
    val dayOfMonthList = DateTimePickerUtils.getDayOfMonthList(
        year = LocalDate.now().year,
        month = LocalDate.now().monthValue
    )

    var selectedDayOfMonth: Int by remember { mutableStateOf(LocalDate.now().dayOfMonth) }

    NumberPicker(
        values = dayOfMonthList,
        displayedValues = dayOfMonthList.map { it.toString() },
        selectedValue = selectedDayOfMonth,
        onValueSelected = { selectedDayOfMonth = it },
        modifier = modifier
    )
}

@Composable
private fun MakePartyDateTimeMiddayPicker(modifier: Modifier = Modifier) {
    var selectedMidday: Int by remember { mutableStateOf(0) }

    NumberPicker(
        values = DateTimePickerUtils.midday,
        displayedValues = DateTimePickerUtils.displayedValuesForMidday,
        selectedValue = selectedMidday,
        onValueSelected = { selectedMidday = it },
        modifier = modifier
    )
}

@Composable
private fun MakePartyDateTimeHourPicker(modifier: Modifier = Modifier) {
    var selectedHour: Int by remember { mutableStateOf(LocalTime.now().hour) }

    NumberPicker(
        values = DateTimePickerUtils.hours,
        displayedValues = DateTimePickerUtils.displayedValuesForHours,
        selectedValue = selectedHour,
        onValueSelected = { selectedHour = it },
        modifier = modifier
    )
}

@Composable
private fun MakePartyDateTimeMinutePicker(modifier: Modifier = Modifier) {
    var selectedMinute: Int by remember { mutableStateOf(LocalTime.now().minute) }

    NumberPicker(
        values = DateTimePickerUtils.minutes,
        displayedValues = DateTimePickerUtils.displayedValuesForMinutes,
        selectedValue = selectedMinute,
        onValueSelected = { selectedMinute = it },
        modifier = modifier
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MakePartyDateTimePickerDialogPreview() {
    WantRunningTheme {
        MakePartyDateTimePickerDialog(modifier = Modifier.padding(all = 12.dp))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeNumberPickerPreview() {
    WantRunningTheme {
        MakePartyDateTimeNumberPicker()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeYearPickerPreview() {
    WantRunningTheme {
        MakePartyDateTimeYearPicker()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeMonthPickerPreview() {
    WantRunningTheme {
        MakePartyDateTimeMonthPicker()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeDayOfMonthPickerPreview() {
    WantRunningTheme {
        MakePartyDateTimeDayOfMonthPicker()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeMiddayPickerPreview() {
    WantRunningTheme {
        MakePartyDateTimeMiddayPicker()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeHourPickerPreview() {
    WantRunningTheme {
        MakePartyDateTimeHourPicker()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeMinutePickerPreview() {
    WantRunningTheme {
        MakePartyDateTimeMinutePicker()
    }
}
