package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ITEM
import com.example.comp3717_wo_miniapp.data.SHIELD
import com.example.comp3717_wo_miniapp.data.Shield
import com.example.comp3717_wo_miniapp.data.Shields
import com.google.gson.Gson
import io.ktor.client.HttpClient

class ShieldRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingHttpRepository<Shield>() {

    /**
     * fetch shield items from the API.
     * Optionally specify search terms
     * Optionally specify page of results
     */
    override suspend fun getItems(searchTerms: String?, page: Int): Shields {
        val responseString = super.getItemsJson(SHIELD, searchTerms, page)
        return Gson().fromJson(responseString, Shields::class.java)
    }

    override suspend fun getItem(itemId: String): Shield {
        TODO("Not yet implemented")
    }
}