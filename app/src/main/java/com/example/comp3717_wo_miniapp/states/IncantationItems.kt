package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.Incantation

class IncantationItems (override val repo: EldenRingRepo) : EldenRingItemGroup<Incantation> {

    override val items = mutableStateListOf<ItemState<Incantation>>()
    override var page = mutableIntStateOf(0)
    override var searchTerms = mutableStateOf("")

    override suspend fun getItems() {
        val response = repo.getIncantations(searchTerms.value, page.intValue)

        if (response.data.isNotEmpty()) {
            items.clear()
            items.addAll(response.data.map {
                ItemState(it)
            })
        }
    }
}