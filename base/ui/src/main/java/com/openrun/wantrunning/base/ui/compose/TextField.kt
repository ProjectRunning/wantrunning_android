package com.openrun.wantrunning.base.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BasicTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String,
    errorMessage: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    isError: Boolean = false,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onValueChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 6.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange,
            placeholder = {
                Text(
                    text = hint,
                    color = Gray20,
                    style = MaterialTheme.typography.body2
                )
            },
            enabled = enabled,
            readOnly = readOnly,
            isError = isError,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            singleLine = singleLine,
            shape = RoundedCornerShape(size = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Gray100,
                backgroundColor = Gray1,
                focusedIndicatorColor = if (readOnly) {
                    Color.Transparent
                } else {
                    MaterialTheme.colors.primary
                },
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.body2
        )

        if (isError) {
            TextFieldErrorText(errorMessage = errorMessage)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun BasicTextFieldPreview() {
    WantRunningTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            BasicTextField(
                value = "",
                hint = "텍스트를 입력해주세요.",
                onValueChange = {}
            )

            BasicTextField(
                value = "텍스트를 입력했습니다.",
                hint = "",
                onValueChange = {}
            )

            BasicTextField(
                value = "",
                hint = "텍스트를 입력해주세요.",
                errorMessage = "에러 메세지입니다.",
                isError = true,
                onValueChange = {}
            )
        }
    }
}