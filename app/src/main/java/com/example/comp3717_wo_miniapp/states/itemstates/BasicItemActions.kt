package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.composables.ItemInfo.ItemInfo
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository

class BasicItemActions <T : ItemData> : EldenRingItemActions<T> {

    @Composable
    override fun GetItemInfo(item: T, onCloseAction: () -> Unit) {
        println("Item info $item")
        ItemInfo(item, onCloseAction)
    }

    override fun saveItem(item: T, eldenRingHttpRepository: EldenRingHttpRepository<T>) {
        println("Item saved ${item}, repository ${eldenRingHttpRepository}")
    }
}