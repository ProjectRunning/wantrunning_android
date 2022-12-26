package com.openrun.wantrunning.ui.component

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.openrun.wantrunning.ui.WantRunningTheme

@Composable
fun NumberPicker(
    modifier: Modifier = Modifier,
    displayedValues: List<String>,
    selectedValue: Int,
    onValueSelected: (value: Int) -> Unit
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            android.widget.NumberPicker(context).apply {
                this.displayedValues = displayedValues.toTypedArray()
                this.minValue = 0
                this.maxValue = displayedValues.lastIndex
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
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun NumberPickerPreview() {
    WantRunningTheme {
        NumberPicker(displayedValues = List(100) { it.toString() }, selectedValue = 33, onValueSelected = {})
    }
}