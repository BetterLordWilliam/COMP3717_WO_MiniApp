package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.Talisman

class TalismanItems (override val repo : EldenRingRepo) : EldenRingItemGroup<Talisman> {
    override val items = mutableStateListOf<ItemState<Talisman>>()
    override var page = mutableIntStateOf(0)
    override var searchTerms = mutableStateOf("")

    override suspend fun getItems() {
        val response = repo.getTalismans(searchTerms.value, page.intValue)

        if (response.data.isNotEmpty()) {
            items.clear()
            items.addAll(response.data.map {
                ItemState(it)
            })
        }
    }
}