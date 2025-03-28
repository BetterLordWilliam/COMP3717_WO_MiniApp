package com.example.comp3717_wo_miniapp.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.comp3717_wo_miniapp.EldenRingUIState
import com.example.comp3717_wo_miniapp.ItemCardUIState
import com.example.comp3717_wo_miniapp.data.Armour
import com.example.comp3717_wo_miniapp.data.ItemData

@Composable
fun <T : ItemData> ItemList(
    listItems:              SnapshotStateList<ItemCardUIState<T>>?,
) {
    LazyColumn (
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        if (listItems != null)
        items(listItems) { listItem ->
            ItemCard(listItem)
        }
        else {
            item {
                Text("No items to show")
            }
        }
    }
}