package com.openrun.wantrunning.ui.component

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import com.openrun.wantrunning.ui.Gray1
import com.openrun.wantrunning.ui.WantRunningTheme

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    values: List<Int>,
    displayedValues: List<String>,
    selectedValue: Int,
    onValueSelected: (value: Int) -> Unit
) {
    Box(modifier = modifier) {
        AndroidView(
            modifier = Modifier.zIndex(zIndex = 1f),
            factory = { context ->
                android.widget.NumberPicker(context).apply {
                    this.displayedValues = displayedValues.toTypedArray()
                    this.minValue = values.first()
                    this.maxValue = values.last()
                    this.value = selectedValue

                    this.setOnValueChangedListener { _, _, newVal ->
                        onValueSelected.invoke(newVal)
                    }

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        this.selectionDividerHeight = 0
                    }
                }
            }
        )

        Box(
            modifier = Modifier
                .size(size = 56.dp)
                .fillMaxWidth()
                .background(color = Gray1, shape = RoundedCornerShape(size = 8.dp))
                .align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun NumberPickerPreview() {
    WantRunningTheme {
        NumberPicker(
            values = List(100) { it },
            selectedValue = 33,
            onValueSelected = {},
            displayedValues = List(size = 100) { (it + 1).toString() }
        )
    }
}