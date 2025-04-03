package com.example.comp3717_wo_miniapp.composables.ItemListComposables

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.comp3717_wo_miniapp.ItemType

@Composable
fun ItemTypeSelector (
    selectedItemType:       ItemType,
    onItemTypeSelected:     (ItemType) -> Unit
) {
    Row(modifier = Modifier.horizontalScroll(rememberScrollState()).padding(8.dp)) {
        ItemType.entries.forEach { category ->
            Button(
                onClick = { onItemTypeSelected(category) },
                modifier = Modifier.padding(end = 8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (category == selectedItemType) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                )
            ) {
                Text(category.name)
            }
        }
    }
}