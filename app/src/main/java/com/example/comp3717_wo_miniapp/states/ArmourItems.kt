package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.comp3717_wo_miniapp.data.Armour
import com.example.comp3717_wo_miniapp.data.EldenRingRepo

class ArmourItems(override val repo: EldenRingRepo) : EldenRingItemGroup<Armour> {

    override val items = mutableStateListOf<ItemState<Armour>>()
    override var page = mutableIntStateOf(0)
    override var searchTerms = mutableStateOf("")

    override suspend fun getItems() {
        val armoursRes = repo.getArmours(searchTerms.value, page.intValue)

        if (armoursRes.data.isNotEmpty()) {
            items.clear()
            items.addAll(armoursRes.data.map {
                ItemState(it)
            })
        }
    }
}