package com.openrun.wantrunning.base.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.ui.Gray1
import com.openrun.wantrunning.ui.Gray100
import com.openrun.wantrunning.ui.Gray20
import com.openrun.wantrunning.ui.WantRunningTheme

@Composable
fun BoxedTextField(
    modifier: Modifier = Modifier,
    value: String,
    hint: String = "",
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    textStyle: TextStyle = MaterialTheme.typography.body2,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
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
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        textStyle = textStyle
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun BasicTextFieldPreview() {
    WantRunningTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            BoxedTextField(
                value = "",
                hint = "텍스트를 입력해주세요.",
                onValueChange = {}
            )

            BoxedTextField(
                value = "텍스트를 입력했습니다.",
                onValueChange = {}
            )
        }
    }
}