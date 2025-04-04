package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.SORCERY
import com.example.comp3717_wo_miniapp.data.models.Sorceries
import com.example.comp3717_wo_miniapp.data.models.Sorcery
import com.google.gson.Gson
import io.ktor.client.HttpClient

class SorceryRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingHttpRepository() {

    /**
     * Retrieve sorceries from the elden ring HTTP endpoint.
     * Optionally specify search terms
     * Optionally specify page
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Sorcery> {
        val responseString = super.getItemsJson(SORCERY, searchTerms, page)
        return Gson().fromJson(responseString, Sorceries::class.java).data
    }

    override suspend fun getItem(itemId: String): Sorcery {
        TODO("Not yet implemented")
    }
}