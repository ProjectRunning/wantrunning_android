package com.openrun.wantrunning.ui.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas

fun Modifier.shadow(
    color: Color = Color.Black,

): Modifier = composed {
    drawBehind {
        drawIntoCanvas { canvas: Canvas ->
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()

            canvas.drawRoundRect()
        }
    }
}