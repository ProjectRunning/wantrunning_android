package com.openrun.wantrunning.party.make

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.R
import com.openrun.wantrunning.base.ui.compose.*

@Composable
fun MakePartyEssentialScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(scrollState, enabled = true)
                .padding(PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)),
            verticalArrangement = Arrangement.spacedBy(space = 24.dp)
        ) {
            var title by rememberSaveable { mutableStateOf("") }
            var titleErrorMessage by rememberSaveable { mutableStateOf("") }
            MakePartyTitleContent(
                title = title,
                errorMessage = titleErrorMessage,
                onTitleChange = {
                    title = it
                }
            )

            var address by rememberSaveable { mutableStateOf("") }
            var addressErrorMessage by rememberSaveable { mutableStateOf("") }
            MakePartyRegionContent(
                address = address,
                errorMessage = addressErrorMessage
            ) {

            }

            var date by rememberSaveable { mutableStateOf("") }
            var time by rememberSaveable { mutableStateOf("") }
            var dateTimeErrorMessage by rememberSaveable { mutableStateOf("") }
            MakePartyDatetimeContent(
                date = date,
                time = time,
                errorMessage = dateTimeErrorMessage
            ) {

            }

            var personnel by rememberSaveable { mutableStateOf(2) }
            MakePartyPersonnelContent(
                personnel = personnel,
                onMinusButtonClick = { /*TODO*/ },
                onPlusButtonClick = {}
            )

            var url by rememberSaveable { mutableStateOf("") }
            var urlErrorMessage by rememberSaveable { mutableStateOf("") }
            MakePartyOpenChattingContent(
                url = url,
                onUrlChange = {},
                urlErrorMessage = urlErrorMessage
            )
        }

        var nextButtonEnabled by rememberSaveable { mutableStateOf(false) }
        BasicButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            text = "다음으로",
            enabled = nextButtonEnabled
        ) {

        }
    }

}

@Composable
private fun MakePartyTitleContent(
    modifier: Modifier = Modifier,
    title: String,
    errorMessage: String,
    onTitleChange: (title: String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        TextFieldTitleText(
            title = "파티 이름",
            isEssentialField = true
        )

        BasicTextField(
            value = title,
            onValueChange = onTitleChange,
            errorMessage = errorMessage,
            hint = "파티 이름을 입력해주세요.",
            imeAction = ImeAction.Next
        )
    }
}

@Composable
private fun MakePartyRegionContent(
    modifier: Modifier = Modifier,
    address: String,
    errorMessage: String,
    onRegionButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        TextFieldTitleText(
            title = "파티 지역",
            isEssentialField = true
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            BasicTextField(
                modifier = Modifier.weight(1f),
                value = address,
                onValueChange = {},
                errorMessage = errorMessage,
                hint = "파티 지역을 입력해주세요.",
                readOnly = true
            )

            BasicIconButton(
                iconResId = R.drawable.ic_location_24,
                onClick = onRegionButtonClick
            )
        }
    }
}

@Composable
private fun MakePartyDatetimeContent(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    errorMessage: String,
    onDateTimeButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextFieldTitleText(
            title = "파티 일정",
            isEssentialField = true
        )

        Spacer(modifier = Modifier.size(12.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            BasicTextField(
                modifier = Modifier.weight(1.3f),
                value = date,
                hint = "날짜",
                readOnly = true,
                onValueChange = {}
            )
            BasicTextField(
                modifier = Modifier.weight(1f),
                value = time,
                hint = "시간",
                readOnly = true,
                onValueChange = {}
            )
            BasicIconButton(
                iconResId = R.drawable.ic_calendar_24,
                onClick = onDateTimeButtonClick
            )
        }

        Spacer(modifier = Modifier.size(6.dp))

        if (errorMessage.isNotBlank()) {
            TextFieldErrorText(errorMessage = errorMessage)
        }
    }
}

@Composable
private fun MakePartyPersonnelContent(
    modifier: Modifier = Modifier,
    personnel: Int,
    onMinusButtonClick: () -> Unit,
    onPlusButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextFieldTitleText(
            title = "참여 인원",
            isEssentialField = true
        )

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            BasicIconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                iconResId = if (personnel == 2) {
                    R.drawable.ic_minus_24_disabled
                } else {
                    R.drawable.ic_minus_24_enabled
                },
                enabled = personnel > 2,
                onClick = onMinusButtonClick
            )

            BasicTextField(
                modifier = Modifier.weight(1f),
                value = personnel.toString(),
                hint = "",
                readOnly = true,
                onValueChange = {},
                textStyle = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center)
            )

            BasicIconButton(
                modifier = Modifier.align(Alignment.CenterVertically),
                iconResId = if (personnel == 10) {
                    R.drawable.ic_plus_24_disabled
                } else {
                    R.drawable.ic_plus_24_enabled
                },
                enabled = personnel < 10,
                onClick = onPlusButtonClick
            )
        }
    }
}

@Composable
private fun MakePartyOpenChattingContent(
    modifier: Modifier = Modifier,
    url: String,
    onUrlChange: (url: String) -> Unit,
    urlErrorMessage: String
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(space = 12.dp)
    ) {
        TextFieldTitleText(
            title = "오픈채팅방 링크",
            isEssentialField = true
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
        ) {
            BasicTextField(
                modifier = Modifier.weight(1f),
                value = url,
                onValueChange = onUrlChange,
                errorMessage = urlErrorMessage,
                hint = "https://open.kakao.com/",
                readOnly = false
            )

            BasicIconButton(
                iconResId = R.drawable.ic_qr_code_24,
                onClick = {}
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyTitleContentPreview() {
    WantRunningTheme {
        MakePartyTitleContent(
            title = "",
            errorMessage = "",
            onTitleChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyRegionContentPreview() {
    WantRunningTheme {
        MakePartyRegionContent(
            address = "서울특별시 마포구 양화로 160 홍대입구역",
            errorMessage = "",
            onRegionButtonClick = {}
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyPersonnelContentPreview() {
    WantRunningTheme {
        MakePartyPersonnelContent(
            personnel = 3,
            onMinusButtonClick = {},
            onPlusButtonClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyOpenChattingContentPreview() {
    WantRunningTheme {
        MakePartyOpenChattingContent(
            url = "",
            onUrlChange = {

            },
            urlErrorMessage = "파티원과 소통할 오픈채팅방 링크를 입력해주세요."
        )
    }
}
