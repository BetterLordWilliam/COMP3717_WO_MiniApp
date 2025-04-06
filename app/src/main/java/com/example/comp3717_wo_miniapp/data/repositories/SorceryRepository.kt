package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.SORCERY
import com.example.comp3717_wo_miniapp.data.dataobjects.SorceryDao
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.entites.toIncantation
import com.example.comp3717_wo_miniapp.data.models.Sorceries
import com.example.comp3717_wo_miniapp.data.models.Sorcery
import com.example.comp3717_wo_miniapp.data.models.toIncantationEntity
import com.example.comp3717_wo_miniapp.data.models.toSorceryEntity
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SorceryRepository(
    override val eldenRingHttpClient: HttpClient,
    private val statDao: StatDao,
    private val sorceryDao: SorceryDao

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
        val sorceryFlow = if (searchString == null) {
            sorceryDao.getItems(page)
        } else {
            sorceryDao.getItems(searchString, page)
        }
        return sorceryFlow.map { sorceryEntityWithStatsList ->
            sorceryEntityWithStatsList.map { sorceryEntityWithStats ->
                sorceryEntityWithStats.toIncantation()
            }
        }
    }

    override suspend fun removeItemFromDatabase(item: Sorcery) {
        sorceryDao.deleteItem(item.toSorceryEntity())
    }

    override suspend fun saveItemToDatabase(item: Sorcery) {
        val sorceryEntity = item.toSorceryEntity()

        sorceryDao.insertItem(sorceryEntity)

        item.requires?.let {
            it.forEach { incantation -> incantation.parentId = item.id }
            statDao.insertReqAtStats(it)
        }
    }
}