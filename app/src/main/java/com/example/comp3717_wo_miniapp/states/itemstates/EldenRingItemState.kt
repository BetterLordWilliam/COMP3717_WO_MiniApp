package com.example.comp3717_wo_miniapp.states.itemstates

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.repositories.EldenRingHttpRepository
import com.example.comp3717_wo_miniapp.states.ItemCardState

class EldenRingItemState <T : ItemData> (
    val repo:           EldenRingHttpRepository<T>,
    val itemActions:    EldenRingItemActions

) : EldenRingItemActions by itemActions {

    val items = mutableStateListOf<ItemCardState<T>>()
    var page = mutableIntStateOf(0 )
    var searchTerms = mutableStateOf("")

    fun incrementPage() {
        page.intValue++
    }

    fun decrementPage() {
        if (page.intValue == 0)
            return
        page.intValue--
    }

    suspend fun getItems() {
        val response = repo.getItems(searchTerms.value, page.intValue)

        if (response.data.isNotEmpty()) {
            items.clear()
            items.addAll(response.data.map {
                ItemCardState(it)
            })
        }
    }
}