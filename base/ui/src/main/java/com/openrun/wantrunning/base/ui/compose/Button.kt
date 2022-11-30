package com.openrun.wantrunning.base.ui.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.R

@Composable
fun BasicIconButton(
    modifier: Modifier = Modifier,
    iconResId: Int,
    enabled: Boolean = true
) {
    IconButton(
        modifier = modifier
            .size(size = 48.dp)
            .border(width = 2.dp, color = Gray5, shape = RoundedCornerShape(size = 8.dp)),
        enabled = enabled,
        onClick = { /*TODO*/ }
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun BasicIconButtonPreview() {
    WantRunningTheme {
        BasicIconButton(iconResId = R.drawable.ic_location_24)
    }
}