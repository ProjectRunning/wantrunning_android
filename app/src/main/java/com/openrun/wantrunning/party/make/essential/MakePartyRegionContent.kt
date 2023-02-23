package com.openrun.wantrunning.party.make.essential

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.WantRunningTheme
import com.openrun.wantrunning.ui.component.BaseIconButton
import com.openrun.wantrunning.ui.component.BoxedTextField
import com.openrun.wantrunning.ui.component.TextFieldErrorText
import com.openrun.wantrunning.ui.component.TextFieldTitleText

@Composable
fun MakePartyRegionContent(
    modifier: Modifier = Modifier,
    address: String,
    errorMessage: String,
    onRegionButtonClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        TextFieldTitleText(
            title = stringResource(id = R.string.make_party_region),
            isEssentialField = true
        )

        MakePartyRegionTextField(
            address = address,
            onRegionButtonClick = onRegionButtonClick
        )

        if (errorMessage.isNotBlank()) {
            TextFieldErrorText(errorMessage = errorMessage)
        }
    }
}

@Composable
private fun MakePartyRegionTextField(
    modifier: Modifier = Modifier,
    address: String,
    onRegionButtonClick: () -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        BoxedTextField(
            value = address,
            hint = stringResource(id = R.string.make_party_region_hint),
            readOnly = true,
            modifier = Modifier.weight(1f),
            onValueChange = {}
        )

        Spacer(modifier = Modifier.size(size = 8.dp))

        BaseIconButton(onClick = onRegionButtonClick, icon = painterResource(id = R.drawable.ic_location_24))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
private fun MakePartyRegionContentPreview() {
    WantRunningTheme {
        MakePartyRegionContent(
            address = "서울특별시 마포구 양화로 160 홍대입구역",
            errorMessage = "",
            onRegionButtonClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyRegionTextFieldPreview() {
    WantRunningTheme {
        MakePartyRegionTextField(
            address = "부산광역시 부산진구 서면문화로 53번길 15-5",
            onRegionButtonClick = {}
        )
    }
}
