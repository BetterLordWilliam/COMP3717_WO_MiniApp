package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ARMOUR
import com.example.comp3717_wo_miniapp.data.dataobjects.ArmourDao
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.entites.ArmourEntity
import com.example.comp3717_wo_miniapp.data.entites.ArmourWithStats
import com.example.comp3717_wo_miniapp.data.entites.toArmour
import com.example.comp3717_wo_miniapp.data.models.Armour
import com.example.comp3717_wo_miniapp.data.models.Armours
import com.example.comp3717_wo_miniapp.data.models.toArmourEntity
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArmourRepository(
    override val eldenRingHttpClient:   HttpClient,
    private val statDao:                        StatDao,
    private val armourDao:                      ArmourDao

) : EldenRingItemRepository<Armour>() {

    /**
     * Get all armours.
     * Optional, specify keywords
     * Optional, specify page of results
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Armour> {
        val responseJson = super.getItemsJson(ARMOUR, searchTerms, page)
        return Gson().fromJson(responseJson, Armours::class.java).data
    }


    /**
     * Load armour items from the database.
     */
    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Armour>> {

        val armourEntities = if (searchString == null) {
            armourDao.getItems(page)
        } else {
            armourDao.getItems(searchString, page)
        }

        return armourEntities.map {
           it.map { armourWithStats ->
                armourWithStats.toArmour()
           }
        }
    }

    /**
     * Removes a piece of armour from the database.
     */
    override suspend fun removeItemFromDatabase(item: Armour) {
        armourDao.deleteItem(item.toArmourEntity())
    }

    /**
     * Save armour items to the database.
     */
    override suspend fun saveItemToDatabase(item: Armour) {

        val armourEntity = item.toArmourEntity()

        armourDao.insertItem(armourEntity)

        item.resistance?.let {
            item.resistance.forEach { it.parentId = item.id }
            statDao.insertResistanceStats(item.resistance)
        }
        item.dmgNegation?.let {
            item.dmgNegation.forEach { it.parentId = item.id }
            statDao.insertDamageNegationStats(item.dmgNegation)
        }
    }
}
