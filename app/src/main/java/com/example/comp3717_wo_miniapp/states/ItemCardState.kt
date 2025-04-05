package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.comp3717_wo_miniapp.data.entites.ItemData

class ItemCardState <T : ItemData>(
    val itemData: T
) {
    var expanded by mutableStateOf<Boolean>(false)
}