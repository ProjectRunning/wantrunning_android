package com.openrun.wantrunning.party.make

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.compose.BasicTextField
import com.openrun.wantrunning.base.ui.compose.TextFieldTitleText
import com.openrun.wantrunning.base.ui.compose.WantRunningTheme

@Composable
fun MakePartyAdditionalScreen(
    modifier: Modifier = Modifier
) {

}

@Composable
private fun MakePartyLevelContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_level))
        Spacer(modifier = Modifier.size(size = 12.dp))
        MakePartyGridRadioGroup(
            items = RunningLevel.displayedValues(),
            onItemSelected = {
                Log.d("Level", "MakePartyLevelContent: $it")
            }
        )
    }
}

@Composable
private fun MakePartyPaceContent(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_pace))
        Spacer(modifier = Modifier.size(size = 12.dp))
        MakePartyGridRadioGroup(
            items = RunningPace.displayedValues(),
            onItemSelected = {
                Log.d("Pace", "MakePartyPaceContent: $it")
            }
        )
    }
}

@Composable
private fun MakePartyTimeTakenContent(modifier: Modifier = Modifier) {
    var timeTaken: String by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextFieldTitleText(title = stringResource(id = R.string.make_party_time_taken))
        Spacer(modifier = Modifier.size(size = 12.dp))
        BasicTextField(
            value = timeTaken,
            hint = stringResource(id = R.string.make_party_time_taken_hint),
            onValueChange = { timeTaken = it }
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyAdditionalScreenPreview() {
    WantRunningTheme {
        MakePartyAdditionalScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyLevelContentPreview() {
    WantRunningTheme {
        MakePartyLevelContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyPaceContentPreview() {
    WantRunningTheme {
        MakePartyPaceContent()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyTimeTakenContentPreview() {
    WantRunningTheme {
        MakePartyTimeTakenContent()
    }
}