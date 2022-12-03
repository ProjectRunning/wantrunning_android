package com.openrun.wantrunning.base.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.R

@Composable
fun BasicIconButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier
            .size(size = 48.dp)
            .border(width = 2.dp, color = Gray5, shape = RoundedCornerShape(size = 8.dp)),
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null
        )
    }
}

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .defaultMinSize(minHeight = 56.dp)
            .fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
        elevation = null,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Primary2,
            disabledBackgroundColor = Gray5
        )
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = if (enabled) Gray0 else Gray20
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BasicIconButtonPreview() {
    WantRunningTheme {
        BasicIconButton(iconResId = R.drawable.ic_location_24) {

        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BasicButtonPreview() {
    WantRunningTheme {
        BasicButton(
            text = "기본버튼입니다."
        ) {

        }
    }
}
