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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.composables.ItemInfoComposables.NumericStatsGridSection
import com.example.comp3717_wo_miniapp.data.models.Sorcery

@Composable
fun SorceryInfo(sorcery: Sorcery) {
    Column (
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .fillMaxHeight()
            .padding(16.dp)
            .verticalScroll(rememberScrollState(0))
    ) {
        Column {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = sorcery.name
            )
            AsyncImage(
                model = sorcery.imageUrl,
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
                        text = sorcery.description,
                    )
                }
                Box (
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(4.dp)
                ) {
                    Text(text = sorcery.effects, style = MaterialTheme.typography.bodyLarge)
                }
                Row (
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Cost: ${sorcery.cost}")
                    Text("Slots: ${sorcery.slots}")
                }
            }
        }
        sorcery.requires?.let { NumericStatsGridSection(title = "Requirements", data = sorcery.requires) }
    }
}
