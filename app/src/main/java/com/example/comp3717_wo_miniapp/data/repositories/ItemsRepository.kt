package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ITEM
import com.example.comp3717_wo_miniapp.data.models.Item
import com.example.comp3717_wo_miniapp.data.models.Items
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class ItemsRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingItemRepository<Item>() {

    /**
     * retrieve all items from the http endpoint.
     * optionally specify search terms
     * optionally specify page number
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Item> {
        val responseString = super.getItemsJson(ITEM, searchTerms, page)
        return Gson().fromJson(responseString, Items::class.java).data
    }

    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Item>> {
        TODO("Not yet implemented")
    }

    override suspend fun removeItemFromDatabase(item: Item) {
        TODO("Not yet implemented")
    }

    override suspend fun saveItemToDatabase(item: Item) {
        TODO("Not yet implemented")
    }
}