package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.TALISMAN
import com.example.comp3717_wo_miniapp.data.models.Talisman
import com.example.comp3717_wo_miniapp.data.models.Talismans
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class TalismanRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingItemRepository<Talisman>() {

    /**
     * Fetch talismans from the ER endpoint
     * Optionally specify search terms
     * Optionally specify results page number
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Talisman> {
        val responseString = super.getItemsJson(TALISMAN, searchTerms, page)
        return Gson().fromJson(responseString, Talismans::class.java).data
    }

    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Talisman>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeItemFromDatabase(item: Talisman) {
        TODO("Not yet implemented")
    }

    override suspend fun saveItemToDatabase(item: Talisman) {
        TODO("Not yet implemented")
    }
}
