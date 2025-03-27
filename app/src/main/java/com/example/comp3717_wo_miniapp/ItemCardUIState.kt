package com.example.comp3717_wo_miniapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.comp3717_wo_miniapp.data.Weapon

class ItemCardUIState(
    val itemData: Weapon
) {
    var expanded by mutableStateOf<Boolean>(false)
}