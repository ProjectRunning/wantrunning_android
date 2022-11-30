package com.openrun.wantrunning.party.make

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.R
import com.openrun.wantrunning.base.ui.compose.BasicIconButton
import com.openrun.wantrunning.base.ui.compose.BasicTextField
import com.openrun.wantrunning.base.ui.compose.TextFieldTitleText
import com.openrun.wantrunning.base.ui.compose.WantRunningTheme

@Composable
fun MakeRunningPartyScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(PaddingValues(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 16.dp)),
        verticalArrangement = Arrangement.spacedBy(space = 24.dp)
    ) {
        var title by rememberSaveable { mutableStateOf("") }
        var titleError by rememberSaveable { mutableStateOf(false) }
        var titleErrorMessage by rememberSaveable { mutableStateOf("") }

        MakeRunningPartyTitleContent(
            title = title,
            isError = titleError,
            errorMessage = titleErrorMessage,
            onTitleChange = {
                title = it
            }
        )

        var address by rememberSaveable { mutableStateOf("") }
        var addressError by rememberSaveable { mutableStateOf(false) }
        var addressErrorMessage by rememberSaveable { mutableStateOf("") }

        MakeRunningPartyRegionContent(
            address = address,
            isError = addressError,
            errorMessage = addressErrorMessage
        )
    }
}

@Composable
private fun MakeRunningPartyTitleContent(
    modifier: Modifier = Modifier,
    title: String,
    isError: Boolean,
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
            isError = isError,
            imeAction = ImeAction.Next
        )
    }
}

@Composable
private fun MakeRunningPartyRegionContent(
    modifier: Modifier = Modifier,
    address: String,
    isError: Boolean,
    errorMessage: String
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
                isError = isError,
                readOnly = true
            )

            BasicIconButton(iconResId = R.drawable.ic_location_24)
        }
    }
}

@Composable
private fun MakeRunningPartyDatetimeContent(
    modifier: Modifier = Modifier,
    date: String,
    time: String,
    isDateError: Boolean,
    isTimeError: Boolean
) {

}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakeRunningPartyTitleContentPreview() {
    WantRunningTheme {
        MakeRunningPartyTitleContent(
            title = "",
            isError = false,
            errorMessage = "",
            onTitleChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakeRunningPartyRegionContentPreview() {
    WantRunningTheme {
        MakeRunningPartyRegionContent(
            address = "서울특별시 마포구 양화로 160 홍대입구역",
            isError = false,
            errorMessage = ""
        )
    }
}
