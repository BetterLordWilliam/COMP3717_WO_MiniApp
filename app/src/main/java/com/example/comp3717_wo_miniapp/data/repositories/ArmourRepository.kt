package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ARMOUR
import com.example.comp3717_wo_miniapp.data.models.Armour
import com.example.comp3717_wo_miniapp.data.models.Armours
import com.google.gson.Gson
import io.ktor.client.HttpClient

class ArmourRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingHttpRepository() {

    /**
     * Get all armours.
     * Optional, specify keywords
     * Optional, specify page of results
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Armour> {
        val responseJson = super.getItemsJson(ARMOUR, searchTerms, page)
        return Gson().fromJson(responseJson, Armours::class.java).data
    }

    /**
     * Get a specific kind of armour
     */
    override suspend fun getItem(itemId: String): Armour {
        TODO("Not yet implemented")
    }
}