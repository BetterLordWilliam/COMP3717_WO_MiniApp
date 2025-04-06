package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.INCANTATION
import com.example.comp3717_wo_miniapp.data.dataobjects.IncantationDao
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.entites.toIncantation
import com.example.comp3717_wo_miniapp.data.models.Incantation
import com.example.comp3717_wo_miniapp.data.models.Incantations
import com.example.comp3717_wo_miniapp.data.models.Item
import com.example.comp3717_wo_miniapp.data.models.toIncantationEntity
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IncantationRepository(
    override val eldenRingHttpClient:   HttpClient,
    private val statsDao:               StatDao,
    private val incantationDao:         IncantationDao

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

    /**
     * Returns a flow of incantation models.
     */
    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Incantation>> {
        val incantationFlow = if (searchString == null) {
            incantationDao.getItems(page)
        } else {
            incantationDao.getItems(searchString, page)
        }
        return incantationFlow.map { incantationEntityWithStatsList ->
            incantationEntityWithStatsList.map { incantationEntityWithStats ->
                incantationEntityWithStats.toIncantation()
            }
        }
    }

    /**
     * Remove incantation item from the database.
     */
    override suspend fun removeItemFromDatabase(item: Incantation) {
        incantationDao.deleteItem(item.toIncantationEntity())
    }

    /**
     * Save an incantation item to the database alongside the stat requirements.
     */
    override suspend fun saveItemToDatabase(item: Incantation) {
        val incantationEntity = item.toIncantationEntity()

        incantationDao.insertItem(incantationEntity)

        item.requires?.let {
            it.forEach { incantation -> incantation.parentId = item.id }
            statsDao.insertReqAtStats(it)
        }
    }
}