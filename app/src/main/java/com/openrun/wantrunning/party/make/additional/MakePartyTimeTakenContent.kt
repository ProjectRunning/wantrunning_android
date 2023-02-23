package com.openrun.wantrunning.party.make.additional

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.Gray5
import com.openrun.wantrunning.ui.Gray50
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.ui.WantRunningTypography
import com.openrun.wantrunning.ui.component.BaseButton
import com.openrun.wantrunning.ui.component.BoxedTextField
import com.openrun.wantrunning.ui.component.TextFieldTitleText

@Composable
fun MakePartyTimeTakenContent(
    modifier: Modifier = Modifier,
    timeTaken: String?,
    onTimeTakenEditButtonClick: () -> Unit
) {
    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_time_taken))

        MakePartyTimeTakenTextField(
            timeTaken = timeTaken,
            onTimeTakenEditButtonClick = onTimeTakenEditButtonClick
        )
    }
}

@Composable
private fun MakePartyTimeTakenTextField(
    modifier: Modifier = Modifier,
    timeTaken: String?,
    onTimeTakenEditButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        BoxedTextField(
            value = timeTaken ?: "",
            hint = stringResource(id = R.string.make_party_time_taken_hint),
            onValueChange = {},
            readOnly = true,
            textStyle = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center)
        )

        BaseButton(
            onClick = onTimeTakenEditButtonClick,
            border = BorderStroke(width = 2.dp, color = Gray5),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
        ) {
            Text(
                text = stringResource(id = R.string.all_edit),
                style = WantRunningTypography.button.copy(color = Gray50, fontSize = 14.sp)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyTimeTakenContentPreview() {
    WantRunningTheme {
        MakePartyTimeTakenContent(
            timeTaken = "00:30",
            onTimeTakenEditButtonClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyTimeTakenTextFieldPreview() {
    WantRunningTheme {
        MakePartyTimeTakenTextField(
            timeTaken = null,
            onTimeTakenEditButtonClick = {}
        )
    }
}
