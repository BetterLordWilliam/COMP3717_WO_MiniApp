package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.mutableStateOf
import com.example.comp3717_wo_miniapp.ItemType
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.states.itemstates.EldenRingItemState

class EldenRingUIState(
    val itemStates : Map<ItemType, EldenRingItemState<out ItemData>>
) {
    var selected = mutableStateOf(itemStates[ItemType.WEAPON])
}