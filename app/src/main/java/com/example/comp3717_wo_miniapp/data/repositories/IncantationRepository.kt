package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.INCANTATION
import com.example.comp3717_wo_miniapp.data.models.Incantation
import com.example.comp3717_wo_miniapp.data.models.Incantations
import com.example.comp3717_wo_miniapp.data.models.Item
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class IncantationRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingItemRepository<Incantation>() {

    /**
     * Retrieve incantations from JSON endpoint.
     * Optionally specify search terms
     * Optionally specify page
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Incantation> {
        val responseString = super.getItemsJson(INCANTATION, searchTerms, page)
        return Gson().fromJson(responseString, Incantations::class.java).data
    }

    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Incantation>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeItemFromDatabase(item: Incantation) {
        TODO("Not yet implemented")
    }

    override suspend fun saveItemToDatabase(item: Incantation) {
        TODO("Not yet implemented")
    }
}