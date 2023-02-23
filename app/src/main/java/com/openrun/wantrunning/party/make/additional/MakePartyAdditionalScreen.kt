package com.openrun.wantrunning.party.make.additional

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.party.make.RunningLevel
import com.openrun.wantrunning.party.make.RunningPace
import com.openrun.wantrunning.ui.Gray20
import com.openrun.wantrunning.ui.Gray5
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.ui.WantRunningTypography
import com.openrun.wantrunning.ui.component.BaseButton

@Composable
fun MakePartyAdditionalScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    var runningLevel: RunningLevel? by remember { mutableStateOf(null) }
    var runningPace: RunningPace? by remember { mutableStateOf(null) }
    val timeTaken: String? by remember { mutableStateOf(null) }
    var introduce: String? by remember { mutableStateOf(null) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState, enabled = true)
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 24.dp)
        ) {
            MakePartyLevelContent(
                selectedLevel = runningLevel,
                onLevelSelected = { runningLevel = it }
            )

            MakePartyPaceContent(
                selectedPace = runningPace,
                onPaceSelected = { runningPace = it }
            )

            MakePartyTimeTakenContent(
                timeTaken = timeTaken,
                onTimeTakenEditButtonClick = {}
            )

            MakePartyIntroduceContent(
                introduce = introduce,
                onIntroduceValueChange = { introduce = it }
            )
        }

        MakePartyAdditionalButtonContainer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp),
            onSkipButtonClick = {},
            onDoneButtonClick = {}
        )
    }
}

@Composable
private fun MakePartyAdditionalButtonContainer(
    modifier: Modifier = Modifier,
    onSkipButtonClick: () -> Unit,
    onDoneButtonClick: () -> Unit
) {
    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(space = 8.dp)) {
        BaseButton(
            onClick = onSkipButtonClick,
            colors = ButtonDefaults.buttonColors(backgroundColor = Gray5),
            modifier = Modifier.weight(weight = 1f)
        ) {
            Text(text = stringResource(id = R.string.all_skip), style = WantRunningTypography.button.copy(color = Gray20))
        }

        BaseButton(onClick = onDoneButtonClick, modifier = Modifier.weight(weight = 1f)) {
            Text(text = stringResource(id = R.string.all_done), style = WantRunningTypography.button)
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL)
@Composable
private fun MakePartyAdditionalScreenPreview() {
    WantRunningTheme {
        MakePartyAdditionalScreen()
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyAdditionalButtonContainerPreview() {
    WantRunningTheme {
        MakePartyAdditionalButtonContainer(
            onSkipButtonClick = {},
            onDoneButtonClick = {}
        )
    }
}
