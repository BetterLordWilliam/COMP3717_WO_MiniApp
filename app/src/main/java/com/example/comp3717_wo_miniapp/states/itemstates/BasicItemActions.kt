package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.data.ItemData

class BasicItemActions : EldenRingItemActions {

    @Composable
    override fun <T : ItemData> GetItemInfo(item: T) {
        println("Item info retrieve ${item}")
    }

    override fun <T : ItemData> saveItem(item: T) {
        println("Item saved ${item}")
    }
}