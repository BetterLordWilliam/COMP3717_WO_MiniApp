package com.example.comp3717_wo_miniapp

import androidx.compose.runtime.mutableStateListOf
import com.example.comp3717_wo_miniapp.data.EldenRingRepo

class EldenRingUIState(
    private val repo: EldenRingRepo
) {

    var weapons = mutableStateListOf<ItemCardUIState>()

    suspend fun getAllWeapons() {
        val weapons_raw = repo.getAllWeapons()
        weapons.addAll(weapons_raw.data.map {
            ItemCardUIState(it)
        })
        println(weapons)
    }

//    suspend fun getAllArmours() {
//        armours = repo.getAllArmours()
//        println(armours)
//    }
}