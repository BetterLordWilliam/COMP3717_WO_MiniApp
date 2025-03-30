package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.mutableStateOf
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.ItemData

class EldenRingUIState(
    private val repo: EldenRingRepo
) {
    val weapons = WeaponItems(repo)
    val armours = ArmourItems(repo)

    val itemGroups = mapOf(
        "weapons" to weapons,
        "armours" to armours
    )

    var selected = mutableStateOf<EldenRingItemGroup<out ItemData>>(weapons)
}