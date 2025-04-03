package com.example.comp3717_wo_miniapp.composables.ItemListComposables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.data.ItemData

@Composable
fun ItemRow(
    itemData:       ItemData,
    onInfoClicked:  () -> Unit,
    onSaveClicked:  () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(model = itemData.imageUrl, contentDescription = "${itemData.name} image")
        Spacer(modifier = Modifier.width(8.dp))
        Text(itemData.name, modifier = Modifier.weight(1f))
        Column {
            IconButton(onClick = onInfoClicked) {
                Icon(Icons.Default.Info, contentDescription = "Show item info")
            }
            IconButton(onClick = onSaveClicked) {
                Icon(Icons.Default.FavoriteBorder, contentDescription = "Save item to favorites")
            }
        }
    }
}