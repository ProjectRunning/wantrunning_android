package com.openrun.wantrunning.party.make.essential

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.R
import com.openrun.wantrunning.base.ui.compose.BasicButton
import com.openrun.wantrunning.base.ui.compose.WantRunningTheme
import com.openrun.wantrunning.party.make.*

@Composable
fun MakePartyEssentialScreen(
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()

    var title by rememberSaveable { mutableStateOf("") }
    var titleErrorMessage by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var addressErrorMessage by rememberSaveable { mutableStateOf("") }
    var date by rememberSaveable { mutableStateOf("") }
    var time by rememberSaveable { mutableStateOf("") }
    var dateTimeErrorMessage by rememberSaveable { mutableStateOf("") }
    var personnel by rememberSaveable { mutableStateOf(2) }
    var url by rememberSaveable { mutableStateOf("") }
    var urlErrorMessage by rememberSaveable { mutableStateOf("") }
    var nextButtonEnabled by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState, enabled = true)
                .padding(top = 8.dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(space = 24.dp)
        ) {
            MakePartyTitleContent(
                title = title,
                errorMessage = titleErrorMessage,
                onTitleChange = { title = it }
            )

            MakePartyRegionContent(
                address = address,
                errorMessage = addressErrorMessage,
                onRegionButtonClick = {}
            )

            MakePartyDatetimeContent(
                date = date,
                time = time,
                errorMessage = dateTimeErrorMessage,
                onDateTimeButtonClick = {}
            )

            MakePartyPersonnelContent(
                personnel = personnel,
                onMinusButtonClick = { /*TODO*/ },
                onPlusButtonClick = {}
            )

            MakePartyOpenChatUrlContent(
                url = url,
                errorMessage = urlErrorMessage,
                onUrlChange = { url = it },
                onQRCodeButtonClick = {}
            )
        }

        BasicButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            text = stringResource(id = R.string.all_go_to_next),
            enabled = nextButtonEnabled,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL)
@Composable
private fun MakePartyEssentialScreenPreview() {
    WantRunningTheme {
        MakePartyEssentialScreen()
    }
}
