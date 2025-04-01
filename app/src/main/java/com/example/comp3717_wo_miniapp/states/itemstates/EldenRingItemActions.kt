package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository

interface EldenRingItemActions <T : ItemData> {

    @Composable
    fun GetItemInfo(item: T, onCloseAction: () -> Unit)

    fun saveItem(item : T, eldenRingHttpRepository: EldenRingHttpRepository<T>)
}