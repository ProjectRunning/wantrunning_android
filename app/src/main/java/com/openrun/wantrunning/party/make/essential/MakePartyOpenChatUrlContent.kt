package com.openrun.wantrunning.party.make.essential

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.ui.component.BaseIconButton
import com.openrun.wantrunning.ui.component.BoxedTextField
import com.openrun.wantrunning.ui.component.TextFieldErrorText
import com.openrun.wantrunning.ui.component.TextFieldTitleText

@Composable
fun MakePartyOpenChatUrlContent(
    modifier: Modifier = Modifier,
    url: String,
    errorMessage: String,
    onUrlChange: (url: String) -> Unit,
    onQRCodeButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        TextFieldTitleText(
            title = stringResource(id = R.string.make_party_open_chat),
            isEssentialField = true
        )

        MakePartyOpenChatUrlTextField(
            url = url,
            onUrlChange = onUrlChange,
            onQRCodeButtonClick = onQRCodeButtonClick
        )

        if (errorMessage.isNotBlank()) {
            TextFieldErrorText(errorMessage = errorMessage)
        }
    }
}

@Composable
private fun MakePartyOpenChatUrlTextField(
    modifier: Modifier = Modifier,
    url: String,
    onUrlChange: (url: String) -> Unit,
    onQRCodeButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BoxedTextField(
            modifier = Modifier.weight(1f),
            value = url,
            onValueChange = onUrlChange,
            hint = "https://open.kakao.com/",
            readOnly = false
        )

        BaseIconButton(onClick = onQRCodeButtonClick, icon = painterResource(id = R.drawable.ic_qr_code_24))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyOpenChatUrlContentPreview() {
    WantRunningTheme {
        MakePartyOpenChatUrlContent(
            url = "",
            onUrlChange = {},
            errorMessage = "파티원과 소통할 오픈채팅방 링크를 입력해주세요.",
            onQRCodeButtonClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyOpenChatUrlTextFieldPreview() {
    WantRunningTheme {
        MakePartyOpenChatUrlTextField(
            url = "https://open.kakao.com/~~~~",
            onUrlChange = {},
            onQRCodeButtonClick = {}
        )
    }
}
