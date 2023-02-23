package com.openrun.wantrunning.party.make.essential

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.ui.component.BaseIconButton
import com.openrun.wantrunning.ui.component.BoxedTextField
import com.openrun.wantrunning.ui.component.TextFieldTitleText

@Composable
fun MakePartyPersonnelContent(
    modifier: Modifier = Modifier,
    personnel: Int,
    onMinusButtonClick: () -> Unit,
    onPlusButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextFieldTitleText(
            title = stringResource(id = R.string.make_party_personnel),
            isEssentialField = true
        )
        MakePartyPersonnelTextField(
            personnel = personnel,
            onMinusButtonClick = onMinusButtonClick,
            onPlusButtonClick = onPlusButtonClick
        )
    }
}

@Composable
private fun MakePartyPersonnelTextField(
    modifier: Modifier = Modifier,
    personnel: Int,
    onMinusButtonClick: () -> Unit,
    onPlusButtonClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        BaseIconButton(
            onClick = onMinusButtonClick,
            icon = painterResource(id = R.drawable.ic_minus_24),
            enabled = personnel > 2,
            modifier = Modifier.size(size = 48.dp)
        )

        BoxedTextField(
            modifier = Modifier.weight(1f),
            value = personnel.toString(),
            readOnly = true,
            onValueChange = {},
            textStyle = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Center)
        )

        BaseIconButton(
            onClick = onPlusButtonClick,
            icon = painterResource(id = R.drawable.ic_plus_24),
            enabled = personnel < 10,
            modifier = Modifier.size(size = 48.dp)
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

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyPersonnelTextFieldPreview() {
    WantRunningTheme {
        Column(verticalArrangement = Arrangement.spacedBy(space = 8.dp)) {
            MakePartyPersonnelTextField(
                personnel = 2,
                onMinusButtonClick = {},
                onPlusButtonClick = {}
            )

            MakePartyPersonnelTextField(
                personnel = 6,
                onMinusButtonClick = {},
                onPlusButtonClick = {}
            )

            MakePartyPersonnelTextField(
                personnel = 10,
                onMinusButtonClick = {},
                onPlusButtonClick = {}
            )
        }
    }
}
