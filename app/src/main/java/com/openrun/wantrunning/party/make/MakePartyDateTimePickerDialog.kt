package com.openrun.wantrunning.party.make

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.ui.component.NumberPicker
import java.time.LocalDate

@Composable
fun MakePartyDateTimePickerDialog(modifier: Modifier = Modifier) {

}

@Composable
private fun MakePartyDateTimePickerDialogContent(
    modifier: Modifier = Modifier,
    years: List<Int>,
    months: List<Int>
) {
    var selectedYear: Int by remember { mutableStateOf(years.indexOf(LocalDate.now().year)) }
    Column(modifier = modifier) {
        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(space = 4.dp)) {
            MakePartyDateTimeYearPicker(
                years = years,
                selectedYear = selectedYear,
                onYearSelected = { selectedYear = it }
            )
        }
    }
}

@Composable
private fun MakePartyDateTimeYearPicker(
    modifier: Modifier = Modifier,
    years: List<Int>,
    selectedYear: Int,
    onYearSelected: (year: Int) -> Unit
) {
    NumberPicker(
        modifier = modifier,
        displayedValues = years.map { it.toString() },
        selectedValue = selectedYear,
        onValueSelected = onYearSelected
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimePickerDialogContentPreview() {
    WantRunningTheme {
        MakePartyDateTimePickerDialogContent(
            years = (1970..2100).toList(),
            months = (1..12).toList()
        )
    }
}
