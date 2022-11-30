package com.openrun.wantrunning.base.ui.compose

import androidx.compose.material.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

@Composable
fun WantRunningTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = WantRunningLightColors,
        typography = WantRunningTypography,
        content = content
    )
}

val WantRunningLightColors = lightColors(
    primary = Primary3,
    primaryVariant = Primary1,
    secondary = Secondary5,
    secondaryVariant = Secondary2,
    error = ActiveRed
)
