package com.openrun.wantrunning.party.make

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.R
import com.openrun.wantrunning.base.ui.compose.*

@Composable
fun MakePartyDatetimeContent(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    errorMessage: String,
    onDateTimeButtonClick: () -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {
        TextFieldTitleText(
            title = stringResource(id = com.openrun.wantrunning.party.make.R.string.make_party_datetime),
            isEssentialField = true
        )

        MakePartyDateTimeTextField(date = date, time = time, onDateTimeButtonClick = onDateTimeButtonClick)

        if (errorMessage.isNotBlank()) {
            TextFieldErrorText(errorMessage = errorMessage)
        }
    }
}

@Composable
private fun MakePartyDateTimeTextField(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    onDateTimeButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BoxedTextField(
            modifier = Modifier.weight(1.3f),
            value = date,
            hint = stringResource(id = com.openrun.wantrunning.party.make.R.string.make_party_date_hint),
            readOnly = true,
            onValueChange = {}
        )

        BoxedTextField(
            modifier = Modifier.weight(1f),
            value = time,
            hint = stringResource(id = com.openrun.wantrunning.party.make.R.string.make_party_time_hint),
            readOnly = true,
            onValueChange = {}
        )

        BasicIconButton(
            iconResId = R.drawable.ic_calendar_24,
            onClick = onDateTimeButtonClick
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyDateTimeContentPreview() {
    WantRunningTheme {
        MakePartyDatetimeContent(
            date = "12월 3일 토요일",
            time = "오후 6:00",
            errorMessage = "날짜 및 시간을 입력해주세요.",
            onDateTimeButtonClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyDateTimeTextFieldPreview() {
    MakePartyDateTimeTextField(
        date = "",
        time = "",
        onDateTimeButtonClick = {}
    )
}
