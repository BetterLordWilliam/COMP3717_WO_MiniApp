package com.example.comp3717_wo_miniapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.comp3717_wo_miniapp.data.Armour
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.Weapon

class ItemCardUIState <T : ItemData>(
    val itemData: T
) {
    var expanded by mutableStateOf<Boolean>(false)
}