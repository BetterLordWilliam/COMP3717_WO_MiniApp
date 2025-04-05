package com.example.comp3717_wo_miniapp.composables.ItemInfo

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.models.Armour
import com.example.comp3717_wo_miniapp.data.models.Incantation
import com.example.comp3717_wo_miniapp.data.models.Item
import com.example.comp3717_wo_miniapp.data.models.Shield
import com.example.comp3717_wo_miniapp.data.models.Sorcery
import com.example.comp3717_wo_miniapp.data.models.Talisman
import com.example.comp3717_wo_miniapp.data.models.Weapon

/**
 * Item specific information page.
 */
@Composable
fun ItemInfo (
    itemData: ItemData,
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