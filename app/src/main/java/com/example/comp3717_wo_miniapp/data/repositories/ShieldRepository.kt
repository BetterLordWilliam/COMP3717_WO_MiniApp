package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ITEM
import com.example.comp3717_wo_miniapp.data.SHIELD
import com.example.comp3717_wo_miniapp.data.dataobjects.ShieldDao
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.entites.toShield
import com.example.comp3717_wo_miniapp.data.models.Shield
import com.example.comp3717_wo_miniapp.data.models.Shields
import com.example.comp3717_wo_miniapp.data.models.toShieldEntity
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ShieldRepository(
    override val eldenRingHttpClient: HttpClient,
    private val statDao:    StatDao,
    private val shieldDao:  ShieldDao

) : EldenRingItemRepository<Shield>() {

    /**
     * fetch shield items from the API.
     * Optionally specify search terms
     * Optionally specify page of results
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Shield> {
        val responseString = super.getItemsJson(SHIELD, searchTerms, page)
        return Gson().fromJson(responseString, Shields::class.java).data
    }

    /**
     * Retrieve shield items from the database.
     */
    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Shield>> {
        val shieldFlow = if (searchString == null) {
            shieldDao.getItems(page)
        } else {
            shieldDao.getItems(searchString, page)
        }
        return shieldFlow.map { shieldList ->
            shieldList.map { shieldEntityWithStats ->
                shieldEntityWithStats.toShield()
            }
        }
    }

    override suspend fun removeItemFromDatabase(item: Shield) {
        shieldDao.deleteItem(item.toShieldEntity())
    }

    override suspend fun saveItemToDatabase(item: Shield) {
        val shieldEntity = item.toShieldEntity()

        shieldDao.insertItem(shieldEntity)

        item.attack?.let {
            item.attack.forEach { it.parentId = item.id }
            statDao.insertAttackStats(it)
        }
        item.defence?.let {
            item.defence.forEach { it.parentId = item.id }
            statDao.insertDefenceStats(item.defence)
        }
        item.reqAt?.let {
            item.reqAt.forEach { it.parentId = item.id }
            statDao.insertReqAtStats(item.reqAt)
        }
        item.scalesWith?.let {
            item.scalesWith.forEach { it.parentId = item.id }
            statDao.insertScalingStats(item.scalesWith)
        }
    }
}