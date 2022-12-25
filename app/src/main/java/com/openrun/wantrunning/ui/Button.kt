package com.openrun.wantrunning.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R

@Composable
fun BasicIconButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier.border(width = 2.dp, color = Gray5, shape = RoundedCornerShape(size = 8.dp)),
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            tint = if (enabled) Gray50 else Gray10,
            contentDescription = null
        )
    }
}

@Composable
fun BasicTextButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier.border(width = 2.dp, color = Gray5, shape = RoundedCornerShape(size = 8.dp)),
        onClick = onClick
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 14.dp),
            style = MaterialTheme.typography.body2,
            color = Gray50
        )
    }
}

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Primary2,
        disabledBackgroundColor = Gray5,
        contentColor = Gray0,
        disabledContentColor = Gray20
    )
) {
    Button(
        modifier = modifier.defaultMinSize(minHeight = 56.dp),
        onClick = onClick,
        enabled = enabled,
        elevation = null,
        shape = RoundedCornerShape(8.dp),
        colors = buttonColors
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = buttonColors.contentColor(enabled = enabled).value,
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
private fun BasicTextButtonPreview() {
    WantRunningTheme {
        BasicTextButton(
            text = "텍스트 버튼",
            onClick = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BasicButtonPreview() {
    WantRunningTheme {
        BasicButton(
            text = "기본버튼입니다.",
            onClick = {}
        )
    }
}
