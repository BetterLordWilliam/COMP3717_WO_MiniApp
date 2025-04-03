package com.example.comp3717_wo_miniapp.composables.ItemInfo

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.comp3717_wo_miniapp.data.Armour
import com.example.comp3717_wo_miniapp.data.Incantation
import com.example.comp3717_wo_miniapp.data.Item
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.Shield
import com.example.comp3717_wo_miniapp.data.Sorcery
import com.example.comp3717_wo_miniapp.data.Talisman
import com.example.comp3717_wo_miniapp.data.Weapon

/**
 * Item specific information page.
 */
@Composable
fun ItemInfo (
    itemData:           ItemData,
    onDismissRequest:   () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(itemData.name) },
        text = {
            when (itemData) {
                is Weapon -> WeaponInfo(weapon = itemData)
                is Armour -> ArmourInfo(armour = itemData)
                is Shield -> ShieldInfo(shield = itemData)
                is Talisman -> BasicInfo(itemData = itemData)
                is Sorcery -> SorceryInfo(sorcery = itemData)
                is Incantation -> IncantationInfo(incantation = itemData)
                is Item -> BasicInfo(itemData = itemData)
                else -> Text("Details not available for this item type.")
            }
        },
        confirmButton = {
            Button(onClick = onDismissRequest) { Text("Close") }
        },
    )
}