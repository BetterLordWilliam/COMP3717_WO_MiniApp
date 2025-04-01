package com.example.comp3717_wo_miniapp.composables.ItemInfo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.composables.ItemInfoComposables.NumericStatsGridSection
import com.example.comp3717_wo_miniapp.data.Incantation
import com.example.comp3717_wo_miniapp.data.Sorcery


@Composable
private fun ItemInfoReal(itemData: Incantation) {
    Column (
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
    ) {
        Column {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = itemData.name
            )
            AsyncImage(
                model = itemData.imageUrl,
                contentDescription = "Item image",
                modifier = Modifier
                    .width(124.dp)
            )
            Column (
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Box (
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(4.dp)
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = itemData.description,
                    )
                }
                Box (
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(4.dp)
                ) {
                    Text(text = itemData.effects, style = MaterialTheme.typography.bodyLarge)
                }
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Cost: ${itemData.cost}")
                    Text("Slots: ${itemData.slots}")
                }
            }
        }
        NumericStatsGridSection(title = "Requirements", data = itemData.requires)
    }
}

/**
 * Item specific information page.
 */
@Composable
fun IncantationInfo (itemData: Incantation, onCloseAction: () -> Unit) {
    Box {
        ItemInfoReal(itemData)
        IconButton(
            onCloseAction,
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Icon(Icons.Default.Clear, contentDescription = "Dismiss")
        }
    }
}