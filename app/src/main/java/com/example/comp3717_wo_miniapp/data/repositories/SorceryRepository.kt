package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.SORCERY
import com.example.comp3717_wo_miniapp.data.Sorceries
import com.example.comp3717_wo_miniapp.data.Sorcery
import com.google.gson.Gson
import io.ktor.client.HttpClient

class SorceryRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingHttpRepository<Sorcery>() {

    /**
     * Retrieve sorceries from the elden ring HTTP endpoint.
     * Optionally specify search terms
     * Optionally specify page
     */
    override suspend fun getItems(searchTerms: String?, page: Int): Sorceries {
        val responseString = super.getItemsJson(SORCERY, searchTerms, page)
        return Gson().fromJson(responseString, Sorceries::class.java)
    }

    override suspend fun getItem(itemId: String): Sorcery {
        TODO("Not yet implemented")
    }
}