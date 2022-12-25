package com.openrun.wantrunning.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R

@Composable
fun TextFieldTitleText(
    modifier: Modifier = Modifier,
    title: String,
    isEssentialField: Boolean = false
) {
    Row(modifier = modifier) {
        Text(
            text = title,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold)
        )

        if (isEssentialField) {
            Text(
                text = "*",
                style = MaterialTheme.typography.body2,
                color = ActiveRed
            )
        }
    }
}

@Composable
fun TextFieldErrorText(
    modifier: Modifier = Modifier,
    errorMessage: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error_20),
            contentDescription = null
        )

        Text(
            modifier = Modifier.weight(1f),
            text = errorMessage,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.body1
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun TextFieldTitleTextPreview() {
    WantRunningTheme {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            TextFieldTitleText(
                title = "텍스트 입력 타이틀입니다.",
                isEssentialField = true
            )

            TextFieldTitleText(
                title = "텍스트 입력 타이틀입니다.",
                isEssentialField = false
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun TextFieldErrorTextPreview() {
    WantRunningTheme {
        TextFieldErrorText(
            errorMessage = "에러 메시지입니다."
        )
    }
}
