package com.openrun.wantrunning.party.make.essential

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.openrun.wantrunning.ui.*
import com.openrun.wantrunning.ui.component.NumberPicker
import com.openrun.wantrunning.util.DateTimeUtils
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun MakePartyDateTimePickerDialog(isVisible: Boolean, onDismissRequest: () -> Unit, defaultDateTime: LocalDateTime) {
    if (isVisible) {
        Dialog(onDismissRequest = onDismissRequest) {
            MakePartyDateTimePickerDialogContent(
                modifier = Modifier
                    .background(color = Color.White, shape = RoundedCornerShape(size = 8.dp))
                    .padding(all = 12.dp),
                defaultDateTime = defaultDateTime,
                onCompleteButtonClick = {
                    // TODO: on complete
                }
            )
        }
    }
}

@Composable
private fun MakePartyDateTimePickerDialogContent(
    modifier: Modifier = Modifier,
    defaultDateTime: LocalDateTime,
    onCompleteButtonClick: () -> Unit
) {
    var cachedYear: Int by remember { mutableStateOf(defaultDateTime.year) }
    var cachedMonthValue: Int by remember { mutableStateOf(defaultDateTime.monthValue) }
    var cachedDayOfMonth: Int by remember { mutableStateOf(defaultDateTime.dayOfMonth) }

    var cachedMidday: DateTimeUtils.Midday by remember {
        mutableStateOf(DateTimeUtils.getMidday(defaultDateTime))
    }
    var cachedHour: Int by remember {
        mutableStateOf(DateTimeUtils.getMiddayHour(defaultDateTime))
    }
    var cachedMinute: Int by remember { mutableStateOf(defaultDateTime.minute) }

    var isValidDateTime: Boolean by remember { mutableStateOf(false) }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {
        MakePartyDateTimeNumberPicker(
            selectedYear = cachedYear,
            selectedMonthValue = cachedMonthValue,
            selectedDayOfMonth = cachedDayOfMonth,
            selectedMidday = cachedMidday,
            selectedHour = cachedHour,
            selectedMinute = cachedMinute,
            onYearSelected = { cachedYear = it },
            onMonthValueSelected = { cachedMonthValue = it },
            onDayOfMonthSelected = { cachedDayOfMonth = it },
            onMiddaySelected = { cachedMidday = DateTimeUtils.getMiddayByIntValue(intVal = it) },
            onHourSelected = { cachedHour = it },
            onMinuteSelected = { cachedMinute = it },
            onNumberPickerValueChanged = {
                isValidDateTime = DateTimeUtils.isValidDateTime(
                    year = cachedYear,
                    monthValue = cachedMonthValue,
                    dayOfMonth = cachedDayOfMonth,
                    midday = cachedMidday,
                    hour = cachedHour,
                    minute = cachedMinute
                )
            }
        )

        Divider(color = Gray5)

        Spacer(modifier = Modifier.size(size = 8.dp))

        val cachedLocalDate = LocalDate.of(cachedYear, cachedMonthValue, cachedDayOfMonth)
        val patterOfDate = "yyyy년 MM월 dd일 ${DateTimeUtils.getDayOfWeekForKR(cachedLocalDate.dayOfWeek)}"
        Text(
            text = cachedLocalDate.format(DateTimeFormatter.ofPattern(patterOfDate)),
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        val cachedLocalTime = DateTimeUtils.getMiddayLocalTime(cachedMidday, cachedHour, cachedMinute)
        val patterOfTime = "$cachedMidday hh시 mm분"
        Text(
            text = cachedLocalTime.format(DateTimeFormatter.ofPattern(patterOfTime)),
            style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.size(size = 8.dp))

        Text(
            text = if (isValidDateTime) "설정 가능한 일정이에요." else "설정할 수 없는 일정이에요.",
            color = if (isValidDateTime) Gray30 else ActiveRed,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        BasicButton(text = "일정 설정 완료", onClick = onCompleteButtonClick, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun MakePartyDateTimeNumberPicker(
    modifier: Modifier = Modifier,
    selectedYear: Int,
    selectedMonthValue: Int,
    selectedDayOfMonth: Int,
    selectedMidday: DateTimeUtils.Midday,
    selectedHour: Int,
    selectedMinute: Int,
    onYearSelected: (year: Int) -> Unit,
    onMonthValueSelected: (monthValue: Int) -> Unit,
    onDayOfMonthSelected: (dayOfMonth: Int) -> Unit,
    onMiddaySelected: (intValOfMidday: Int) -> Unit,
    onHourSelected: (hour: Int) -> Unit,
    onMinuteSelected: (minute: Int) -> Unit,
    onNumberPickerValueChanged: () -> Unit
) {
    val dayOfMonthList = DateTimeUtils.getDayOfMonthList(year = selectedYear, month = selectedMonthValue)

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(space = 4.dp)) {
        NumberPicker(
            values = DateTimeUtils.years,
            displayedValues = DateTimeUtils.displayedValuesForYears,
            selectedValue = selectedYear,
            onValueSelected = {
                onYearSelected.invoke(it)
                onNumberPickerValueChanged.invoke()
            },
            modifier = Modifier
                .weight(1.2f)
                .onFocusChanged { }
        )

        NumberPicker(
            values = DateTimeUtils.months,
            displayedValues = DateTimeUtils.displayedValuesForMonths,
            selectedValue = selectedMonthValue,
            onValueSelected = {
                onMonthValueSelected.invoke(it)
                onNumberPickerValueChanged.invoke()
            },
            modifier = Modifier.weight(1f)
        )

        NumberPicker(
            values = dayOfMonthList,
            displayedValues = dayOfMonthList.map { it.toString() },
            selectedValue = selectedDayOfMonth,
            onValueSelected = {
                onDayOfMonthSelected.invoke(it)
                onNumberPickerValueChanged.invoke()
            },
            modifier = Modifier.weight(1f)
        )

        NumberPicker(
            values = DateTimeUtils.midday,
            displayedValues = DateTimeUtils.displayedValuesForMidday,
            selectedValue = selectedMidday.intVal,
            onValueSelected = {
                onMiddaySelected.invoke(it)
                onNumberPickerValueChanged.invoke()
            },
            modifier = Modifier.weight(1f)
        )

        NumberPicker(
            values = DateTimeUtils.hours,
            displayedValues = DateTimeUtils.displayedValuesForHours,
            selectedValue = selectedHour,
            onValueSelected = {
                onHourSelected.invoke(it)
                onNumberPickerValueChanged.invoke()
            },
            modifier = Modifier.weight(1f)
        )

        NumberPicker(
            values = DateTimeUtils.minutes,
            displayedValues = DateTimeUtils.displayedValuesForMinutes,
            selectedValue = selectedMinute,
            onValueSelected = {
                onMinuteSelected.invoke(it)
                onNumberPickerValueChanged.invoke()
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
fun MakePartyDateTimePickerDialogContentPreview() {
    WantRunningTheme {
        MakePartyDateTimePickerDialogContent(
            modifier = Modifier.padding(all = 12.dp),
            defaultDateTime = LocalDateTime.now(),
            onCompleteButtonClick = {}
        )
    }
}
