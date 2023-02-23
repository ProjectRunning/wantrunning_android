package com.openrun.wantrunning.party.make.essential

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.component.BoxedTextField
import com.openrun.wantrunning.ui.component.TextFieldErrorText
import com.openrun.wantrunning.ui.component.TextFieldTitleText
import com.openrun.wantrunning.ui.WantRunningTheme

@Composable
fun MakePartyTitleContent(
    modifier: Modifier = Modifier,
    title: String,
    errorMessage: String,
    onTitleChange: (title: String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_title), isEssentialField = true)

        BoxedTextField(
            value = title,
            onValueChange = onTitleChange,
            hint = stringResource(id = R.string.make_party_title_hint),
            imeAction = ImeAction.Next,
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotBlank()) {
            TextFieldErrorText(errorMessage = errorMessage)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyTitleContentPreview() {
    WantRunningTheme {
        Column(verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {
            MakePartyTitleContent(
                title = "",
                errorMessage = "",
                onTitleChange = {}
            )

            MakePartyTitleContent(
                title = "",
                errorMessage = "파티 이름을 입력해주세요.",
                onTitleChange = {}
            )
        }
    }
}
