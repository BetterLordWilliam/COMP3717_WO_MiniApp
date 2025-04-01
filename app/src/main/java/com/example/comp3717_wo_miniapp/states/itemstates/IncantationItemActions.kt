package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.composables.ItemInfo.IncantationInfo
import com.example.comp3717_wo_miniapp.data.Incantation
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository

class IncantationItemActions : EldenRingItemActions<Incantation> {
    @Composable
    override fun GetItemInfo(item: Incantation, onCloseAction: () -> Unit) {
        IncantationInfo(item, onCloseAction)
    }

    override fun saveItem(
        item: Incantation,
        eldenRingHttpRepository: EldenRingHttpRepository<Incantation>
    ) {
        println("Save item ${item}")
    }
}