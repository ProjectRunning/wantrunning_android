package com.openrun.wantrunning.party.make

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.openrun.wantrunning.base.ui.compose.*

@Composable
private fun MakePartyRadioButton(
    modifier: Modifier = Modifier,
    item: String,
    selected: Boolean,
    onSelected: () -> Unit
) {
    Box(
        modifier = modifier
            .selectable(selected = selected, onClick = onSelected)
            .background(
                color = if (selected) Primary8 else Gray1,
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp)
                .align(Alignment.Center),
            text = item,
            style = MaterialTheme.typography.body2.copy(fontWeight = FontWeight.Bold),
            color = if (selected) Primary2 else Gray30
        )
    }
}

@Composable
private fun MakePartyRadioButtonRow(
    modifier: Modifier = Modifier,
    rowItems: List<String>,
    selectedItem: String,
    onItemSelected: (item: String) -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        rowItems.forEach { rowItem ->
            MakePartyRadioButton(
                item = rowItem,
                selected = rowItem == selectedItem,
                onSelected = {
                    onItemSelected.invoke(rowItem)
                }
            )
        }
    }
}

@Composable
fun MakePartyGridRadioGroup(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItem: String = "",
    rowSpan: Int = 3,
    onItemSelected: (item: String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
    ) {
        items.chunked(size = rowSpan).forEach { chunkedList ->
            MakePartyRadioButtonRow(
                rowItems = chunkedList,
                selectedItem = selectedItem,
                onItemSelected = onItemSelected
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyRadioButtonPreview() {
    WantRunningTheme {
        MakePartyRadioButton(
            item = "RadioButton",
            selected = true,
            onSelected = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyRadioButtonRowPreview() {
    WantRunningTheme {
        MakePartyRadioButtonRow(
            rowItems = listOf("radio1", "radio2", "radio3"),
            selectedItem = "radio1",
            onItemSelected = {}
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun MakePartyGridRadioGroupPreview() {
    WantRunningTheme {
        MakePartyGridRadioGroup(
            items = List(size = 8) { "Radio $it" },
            selectedItem = "Radio 4",
            onItemSelected = {}
        )
    }
}