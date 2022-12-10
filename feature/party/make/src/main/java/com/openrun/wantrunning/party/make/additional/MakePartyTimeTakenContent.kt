package com.openrun.wantrunning.party.make.additional

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.compose.BasicTextButton
import com.openrun.wantrunning.base.ui.compose.BoxedTextField
import com.openrun.wantrunning.base.ui.compose.TextFieldTitleText
import com.openrun.wantrunning.base.ui.compose.WantRunningTheme
import com.openrun.wantrunning.party.make.R

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

        BasicTextButton(
            text = stringResource(id = com.openrun.wantrunning.base.ui.R.string.all_edit),
            onClick = onTimeTakenEditButtonClick
        )
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
