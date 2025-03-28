package com.example.comp3717_wo_miniapp

import androidx.compose.runtime.mutableStateListOf
import com.example.comp3717_wo_miniapp.data.Armour
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.Weapon
import kotlinx.coroutines.runBlocking

class EldenRingUIState(
    private val repo: EldenRingRepo
) {

    // State lists
    var weapons = mutableStateListOf<ItemCardUIState<Weapon>>()
    var armours = mutableStateListOf<ItemCardUIState<Armour>>()

    /**
     * Retrieve weapons and set the state list.
     */
    suspend fun getAllWeapons() {
        val weaponsRes = repo.getAllWeapons()
        weapons.addAll(weaponsRes.data.map {
            ItemCardUIState(it)
        })
        println(weapons)
    }

    suspend fun getAllArmours() {
        val armoursRes = repo.getAllArmours()
        armours.addAll(armoursRes.data.map {
            ItemCardUIState(it)
        })
        println(armours)
    }
}