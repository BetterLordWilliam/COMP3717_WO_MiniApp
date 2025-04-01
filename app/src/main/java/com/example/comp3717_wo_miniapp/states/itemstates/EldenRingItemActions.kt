package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.Composable
import com.example.comp3717_wo_miniapp.data.ItemData

interface EldenRingItemActions {

    @Composable
    fun <T : ItemData> GetItemInfo (item : T)

    fun <T : ItemData> saveItem(item : T)
}