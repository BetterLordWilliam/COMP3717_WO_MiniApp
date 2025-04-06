package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ITEM
import com.example.comp3717_wo_miniapp.data.dataobjects.ItemDao
import com.example.comp3717_wo_miniapp.data.entites.toItem
import com.example.comp3717_wo_miniapp.data.entites.toTalisman
import com.example.comp3717_wo_miniapp.data.models.Item
import com.example.comp3717_wo_miniapp.data.models.Items
import com.example.comp3717_wo_miniapp.data.models.toItemEntity
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ItemsRepository(
    override val eldenRingHttpClient: HttpClient,
    private val itemDao: ItemDao

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
        val itemFlow = if (searchString == null) {
            itemDao.getItems(page)
        } else {
            itemDao.getItems(searchString, page)
        }
        return itemFlow.map { itemList ->
            itemList.map { item ->
                item.toItem()
            }
        }
    }

    override suspend fun removeItemFromDatabase(item: Item) {
        itemDao.deleteItem(item.toItemEntity())
    }

    override suspend fun saveItemToDatabase(item: Item) {
        itemDao.insertItem(item.toItemEntity())
    }
}