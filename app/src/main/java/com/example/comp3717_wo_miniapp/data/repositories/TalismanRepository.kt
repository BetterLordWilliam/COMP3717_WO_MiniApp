package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.TALISMAN
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.dataobjects.TalismanDao
import com.example.comp3717_wo_miniapp.data.entites.toIncantation
import com.example.comp3717_wo_miniapp.data.entites.toTalisman
import com.example.comp3717_wo_miniapp.data.models.Talisman
import com.example.comp3717_wo_miniapp.data.models.Talismans
import com.example.comp3717_wo_miniapp.data.models.toSorceryEntity
import com.example.comp3717_wo_miniapp.data.models.toTalismanEntity
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TalismanRepository(
    override val eldenRingHttpClient: HttpClient,
    private val talismanDao: TalismanDao

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
        val talismanFlow = if (searchString == null) {
            talismanDao.getItems(page)
        } else {
            talismanDao.getItems(searchString, page)
        }
        return talismanFlow.map { talismanList ->
            talismanList.map { talisman ->
                talisman.toTalisman()
            }
        }
    }

    override suspend fun removeItemFromDatabase(item: Talisman) {
        talismanDao.deleteItem(item.toTalismanEntity())
    }

    override suspend fun saveItemToDatabase(item: Talisman) {
        val talismanEntity = item.toTalismanEntity()
        talismanDao.insertItem(talismanEntity)
    }
}
