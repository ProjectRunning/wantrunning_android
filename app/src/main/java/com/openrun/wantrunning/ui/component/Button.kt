package com.openrun.wantrunning.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.R
import com.openrun.wantrunning.ui.*

@Composable
fun BaseButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    // no elevation by default
    elevation: ButtonElevation? = null,
    shape: Shape = RoundedCornerShape(size = 8.dp),
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        backgroundColor = Primary2,
        disabledBackgroundColor = Gray5
    ),
    contentPadding: PaddingValues = PaddingValues(all = 16.dp),
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    iconPadding: Dp = 10.dp,
    iconSize: Dp = 20.dp,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        elevation = elevation,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            leadingIcon?.let {
                Image(painter = it, contentDescription = null, modifier = Modifier.size(size = iconSize))

                Spacer(modifier = Modifier.size(size = iconPadding))
            }

            content.invoke(this@Button)

            trailingIcon?.let {
                Spacer(modifier = Modifier.size(size = iconPadding))

                Image(painter = it, contentDescription = null, modifier = Modifier.size(size = iconSize))
            }
        }
    }
}

@Composable
fun BaseIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    icon: Painter,
    iconSize: Dp = 20.dp,
    iconTint: Color = if (enabled) Gray50 else Gray10
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.border(width = 2.dp, color = Gray5, shape = RoundedCornerShape(size = 8.dp)),
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        Icon(painter = icon, contentDescription = null, tint = iconTint, modifier = Modifier.size(size = iconSize))
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL)
@Composable
private fun BaseButtonPreview() {
    WantRunningTheme {
        BaseButton(onClick = { /*TODO*/ }) {
            Text(
                text = "button",
                style = WantRunningTypography.button,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, device = Devices.PIXEL_3_XL)
@Composable
private fun BaseIconButtonPreview() {
    WantRunningTheme {
        BaseIconButton(onClick = { /*TODO*/ }, icon = painterResource(id = R.drawable.ic_lightning_24))
    }
}
