package com.openrun.wantrunning.base.ui.compose

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.openrun.wantrunning.base.ui.R

private val WantRunningFontFamily = FontFamily(
    Font(R.font.pretendard_variable)
)

val WantRunningTypography = androidx.compose.material.Typography(
    defaultFontFamily = WantRunningFontFamily,
    h1 = TextStyle(
        color = Gray100,
        fontSize = 16.sp
    ),
    h2 = TextStyle(
        color = Gray100,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    body1 = TextStyle(
        color = Gray100,
        fontSize = 13.sp
    ),
    body2 = TextStyle(
        color = Gray100,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        color = Gray100,
        fontSize = 10.sp
    )
)