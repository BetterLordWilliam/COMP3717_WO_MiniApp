package com.example.comp3717_wo_miniapp.composables.ItemListComposables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.data.entites.ItemData

@Composable
fun ItemRow(
    itemData: ItemData,
    onButtonOneClicked:  (ItemData) -> Unit,
    onButtonTwoClicked:  (ItemData) -> Unit,
    iconButtonOne: ImageVector = Icons.Default.Info,
    iconButtonTwo: ImageVector = Icons.Default.Favorite
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(model = itemData.imageUrl, contentDescription = "${itemData.name} image")
        Spacer(modifier = Modifier.width(8.dp))
        Text(itemData.name, modifier = Modifier.weight(1f))
        Column {
            IconButton(onClick = { onButtonOneClicked(itemData) }) {
                Icon(iconButtonOne, contentDescription = "Show item info")
            }
            IconButton(onClick = { onButtonTwoClicked(itemData) }) {
                Icon(iconButtonTwo, contentDescription = "Save item to favorites")
            }
        }
    }
}