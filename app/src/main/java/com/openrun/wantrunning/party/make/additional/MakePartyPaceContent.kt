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
import com.openrun.wantrunning.party.make.RunningPace

@Composable
fun MakePartyPaceContent(
    modifier: Modifier = Modifier,
    selectedPace: RunningPace?,
    onPaceSelected: (pace: RunningPace) -> Unit
) {
    Column(modifier = modifier) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_pace))
        Spacer(modifier = Modifier.size(size = 12.dp))
        MakePartyGridRadioGroup(
            items = RunningPace.displayedValues(),
            selectedItem = selectedPace?.displayedValue,
            onItemSelected = {
                val pace = RunningPace.findValueByDisplayedValue(it)
                    ?: return@MakePartyGridRadioGroup
                onPaceSelected.invoke(pace)
            }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyPaceContentPreview() {
    WantRunningTheme {
        MakePartyPaceContent(
            selectedPace = null,
            onPaceSelected = {}
        )
    }
}
