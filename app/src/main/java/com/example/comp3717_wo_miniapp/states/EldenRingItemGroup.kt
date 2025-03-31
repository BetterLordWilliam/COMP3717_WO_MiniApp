package com.example.comp3717_wo_miniapp.states

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.comp3717_wo_miniapp.data.EldenRingRepo
import com.example.comp3717_wo_miniapp.data.ItemData

interface EldenRingItemGroup <T : ItemData> {
    val repo : EldenRingRepo
    val items : SnapshotStateList<ItemState<T>>
    var page : MutableIntState
    var searchTerms : MutableState<String>

    fun incrementPage() {
        page.intValue++
    }
    fun decrementPage() {
        if (page.intValue == 0)
            return
        page.intValue--
    }

    suspend fun getItems()
}