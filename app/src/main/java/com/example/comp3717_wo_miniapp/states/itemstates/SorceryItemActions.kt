package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.composables.ItemInfo.SorceryInfo
import com.example.comp3717_wo_miniapp.composables.ItemInfo.WeaponInfo
import com.example.comp3717_wo_miniapp.data.Sorcery
import com.example.comp3717_wo_miniapp.data.Weapon
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository

class SorceryItemActions : EldenRingItemActions<Sorcery> {
    @Composable
    override fun GetItemInfo(item: Sorcery, onCloseAction: () -> Unit) {
        SorceryInfo(item, onCloseAction)
    }

    override fun saveItem(
        item: Sorcery,
        eldenRingHttpRepository: EldenRingHttpRepository<Sorcery>
    ) {
        println("Save item ${item}")
    }
}