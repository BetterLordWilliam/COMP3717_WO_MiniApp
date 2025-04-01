package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.composables.ItemInfo.ArmourInfo
import com.example.comp3717_wo_miniapp.composables.ItemInfo.WeaponInfo
import com.example.comp3717_wo_miniapp.data.Armour
import com.example.comp3717_wo_miniapp.data.Weapon
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository

class ArmourItemActions : EldenRingItemActions<Armour> {
    @Composable
    override fun GetItemInfo(item: Armour, onCloseAction: () -> Unit) {
        ArmourInfo(item, onCloseAction)
    }

    override fun saveItem(
        item: Armour,
        eldenRingHttpRepository: EldenRingHttpRepository<Armour>
    ) {
        println("Save item ${item}")
    }
}