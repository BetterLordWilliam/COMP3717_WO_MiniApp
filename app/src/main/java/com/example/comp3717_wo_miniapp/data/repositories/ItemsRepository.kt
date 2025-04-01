package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ITEM
import com.example.comp3717_wo_miniapp.data.Item
import com.example.comp3717_wo_miniapp.data.Items
import com.google.gson.Gson
import io.ktor.client.HttpClient

class ItemsRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingHttpRepository<Item>() {

    /**
     * retrieve all items from the http endpoint.
     * optionally specify search terms
     * optionally specify page number
     */
    override suspend fun getItems(searchTerms: String?, page: Int): Items {
        val responseString = super.getItemsJson(ITEM, searchTerms, page)
        return Gson().fromJson(responseString, Items::class.java)
    }

    override suspend fun getItem(itemId: String): Item {
        TODO("Not yet implemented")
    }
}