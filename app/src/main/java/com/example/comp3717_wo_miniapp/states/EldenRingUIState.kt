package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.Incantations
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.Talisman

class EldenRingUIState(
    private val repo: EldenRingRepo
) {
    private val weapons = WeaponItems(repo)
    private val armours = ArmourItems(repo)
    private val items = EldenRingItems(repo)
    private val incantations = IncantationItems(repo)
    private val sorceries = SorceriesItems(repo)
    private val shields = ShieldItems(repo)
    private val talismans = TalismanItems(repo)

    var page = mutableIntStateOf(0)

    val itemGroups = mapOf(
        "weapons" to weapons,
        "shields" to shields,
        "armours" to armours,
        "incantations" to incantations,
        "sorceries" to sorceries,
        "talismans" to talismans,
        "items" to items,
    )

    var selected = mutableStateOf<EldenRingItemGroup<out ItemData>>(weapons)
}