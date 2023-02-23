package com.openrun.wantrunning.party.make.additional

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.component.BoxedTextField
import com.openrun.wantrunning.ui.component.TextFieldTitleText
import com.openrun.wantrunning.ui.WantRunningTheme

@Composable
fun MakePartyIntroduceContent(
    modifier: Modifier = Modifier,
    introduce: String?,
    onIntroduceValueChange: (introduce: String) -> Unit
) {

    Column(modifier = modifier) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_introduce))
        Spacer(modifier = Modifier.size(size = 12.dp))
        BoxedTextField(
            value = introduce ?: "",
            hint = stringResource(id = R.string.make_party_introduce_hint),
            onValueChange = onIntroduceValueChange
        )
    }
}

@Composable
private fun MakePartyIntroduceTextField(
    modifier: Modifier = Modifier,
    introduce: String?,
    onIntroduceValueChange: (introduce: String) -> Unit
) {
    // TODO: height fix
    BoxedTextField(
        value = introduce ?: "",
        onValueChange = onIntroduceValueChange,
        hint = stringResource(id = R.string.make_party_introduce_hint),
        modifier = modifier.defaultMinSize(minHeight = 140.dp),
        textStyle = MaterialTheme.typography.body2.copy(textAlign = TextAlign.Justify)
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyIntroduceContentPreview() {
    WantRunningTheme {
        MakePartyIntroduceContent(
            introduce = "소개문구입니다.",
            onIntroduceValueChange = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyIntroduceTextFieldPreview() {
    WantRunningTheme {
        MakePartyIntroduceTextField(
            introduce = null,
            onIntroduceValueChange = {}
        )
    }
}
