package com.example.comp3717_wo_miniapp.composables.ItemInfo

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat.Style
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.composables.ItemInfoComposables.NumericStatsGridSection
import com.example.comp3717_wo_miniapp.composables.ItemInfoComposables.StringStatsGridSection
import com.example.comp3717_wo_miniapp.data.NumericStatValue
import com.example.comp3717_wo_miniapp.data.StringStatValue
import com.example.comp3717_wo_miniapp.data.models.Weapon

@Composable
fun WeaponInfo(weapon: Weapon) {
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
                text = weapon.name
            )
            AsyncImage(
                model = weapon.imageUrl,
                contentDescription = "Item image",
                modifier = Modifier
                    .width(124.dp)
            )
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                        .padding(4.dp)
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyLarge,
                        text = weapon.description,
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Category: ${weapon.category}")
                    Text("Weight: ${weapon.weight}")
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            NumericStatsGridSection(title = "Attack", data = weapon.attack)
            NumericStatsGridSection(title = "Defense", data = weapon.defence)
            NumericStatsGridSection(title = "Requirements", data = weapon.reqAt)
            StringStatsGridSection(title = "Scales With", data = weapon.scalesWith)
        }
    }
}
