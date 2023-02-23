package com.openrun.wantrunning.party.make.additional

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.component.TextFieldTitleText
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.party.make.MakePartyGridRadioGroup
import com.openrun.wantrunning.party.make.RunningLevel

@Composable
fun MakePartyLevelContent(
    modifier: Modifier = Modifier,
    selectedLevel: RunningLevel?,
    onLevelSelected: (level: RunningLevel) -> Unit
) {
    Column(modifier = modifier) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_level))

        Spacer(modifier = Modifier.size(size = 8.dp))

        MakePartyGridRadioGroup(
            items = RunningLevel.displayedValues(),
            selectedItem = selectedLevel?.displayedValue,
            onItemSelected = {
                val level = RunningLevel.findValueByDisplayedValue(it)
                    ?: return@MakePartyGridRadioGroup
                onLevelSelected.invoke(level)
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyLevelContentPreview() {
    WantRunningTheme {
        MakePartyLevelContent(
            selectedLevel = RunningLevel.ADVANCED,
            onLevelSelected = {}
        )
    }
}
