package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.Weapon

class WeaponItems(override val repo: EldenRingRepo) : EldenRingItemGroup<Weapon> {
    override val items = mutableStateListOf<ItemState<Weapon>>()
    override var page = mutableIntStateOf(0)
    override var searchTerms = mutableStateOf("")

    override fun incrementPage() {
        page.intValue++
    }

    override fun decrementPage() {
        if (page.intValue == 0)
            return
        page.intValue--
    }

    override suspend fun getItems() {
        val weaponsRes = repo.getWeapons(searchTerms.value, page.intValue)

        if (weaponsRes.data.isNotEmpty()) {
            items.clear()
            items.addAll(weaponsRes.data.map {
                ItemState(it)
            })
        }
    }
}