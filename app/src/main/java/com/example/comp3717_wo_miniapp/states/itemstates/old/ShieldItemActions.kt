package com.example.comp3717_wo_miniapp.states.itemstates.old

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.composables.ItemInfo.ShieldInfo
import com.example.comp3717_wo_miniapp.composables.ItemInfo.WeaponInfo
import com.example.comp3717_wo_miniapp.data.Shield
import com.example.comp3717_wo_miniapp.data.Weapon
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository
import com.example.comp3717_wo_miniapp.states.itemstates.EldenRingItemActions

class ShieldItemActions : EldenRingItemActions<Shield> {
    @Composable
    override fun GetItemInfo(item: Shield, onCloseAction: () -> Unit) {
        ShieldInfo(item, onCloseAction)
    }

    override fun saveItem(
        item: Shield,
        eldenRingHttpRepository: EldenRingHttpRepository<Shield>
    ) {
        println("Save item ${item}")
    }
}