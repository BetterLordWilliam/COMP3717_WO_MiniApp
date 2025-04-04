package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.INCANTATION
import com.example.comp3717_wo_miniapp.data.models.Incantation
import com.example.comp3717_wo_miniapp.data.models.Incantations
import com.google.gson.Gson
import io.ktor.client.HttpClient

class IncantationRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingHttpRepository() {

    /**
     * Retrieve incantations from JSON endpoint.
     * Optionally specify search terms
     * Optionally specify page
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Incantation> {
        val responseString = super.getItemsJson(INCANTATION, searchTerms, page)
        return Gson().fromJson(responseString, Incantations::class.java).data
    }

    override suspend fun getItem(itemId: String): Incantation {
        TODO("Not yet implemented")
    }
}