package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.composables.ItemInfo.WeaponInfo
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.Weapon
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository

class WeaponItemActions : EldenRingItemActions<Weapon> {
    @Composable
    override fun GetItemInfo(item: Weapon, onCloseAction: () -> Unit) {
        println("Item info $item")
        WeaponInfo(item, onCloseAction)
    }

    override fun saveItem(
        item: Weapon,
        eldenRingHttpRepository: EldenRingHttpRepository<Weapon>
    ) {
        println("Save item ${item}")
    }
}