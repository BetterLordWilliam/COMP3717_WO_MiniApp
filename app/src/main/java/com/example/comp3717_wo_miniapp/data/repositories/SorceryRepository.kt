package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.SORCERY
import com.example.comp3717_wo_miniapp.data.models.Sorceries
import com.example.comp3717_wo_miniapp.data.models.Sorcery
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class SorceryRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingItemRepository<Sorcery>() {

    /**
     * Retrieve sorceries from the elden ring HTTP endpoint.
     * Optionally specify search terms
     * Optionally specify page
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Sorcery> {
        val responseString = super.getItemsJson(SORCERY, searchTerms, page)
        return Gson().fromJson(responseString, Sorceries::class.java).data
    }

    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Sorcery>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeItemFromDatabase(item: Sorcery) {
        TODO("Not yet implemented")
    }

    override suspend fun saveItemToDatabase(item: Sorcery) {
        TODO("Not yet implemented")
    }
}