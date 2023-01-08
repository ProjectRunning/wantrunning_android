package com.openrun.wantrunning.party.make.essential

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.BasicButton
import com.openrun.wantrunning.ui.WantRunningTheme

@Composable
fun MakePartyEssentialScreen(
    modifier: Modifier = Modifier,
    onRegionButtonClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    var title by rememberSaveable { mutableStateOf("") }
    val titleErrorMessage by rememberSaveable { mutableStateOf("") }
    val address by rememberSaveable { mutableStateOf("") }
    val addressErrorMessage by rememberSaveable { mutableStateOf("") }
    val date by rememberSaveable { mutableStateOf("") }
    val time by rememberSaveable { mutableStateOf("") }
    val dateTimeErrorMessage by rememberSaveable { mutableStateOf("") }
    val personnel by rememberSaveable { mutableStateOf(2) }
    var url by rememberSaveable { mutableStateOf("") }
    val urlErrorMessage by rememberSaveable { mutableStateOf("") }
    val nextButtonEnabled by rememberSaveable { mutableStateOf(false) }

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
                onRegionButtonClick = onRegionButtonClick
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
        MakePartyEssentialScreen(
            onRegionButtonClick = {}
        )
    }
}
