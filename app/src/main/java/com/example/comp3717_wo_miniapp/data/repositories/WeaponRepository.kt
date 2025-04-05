package com.example.comp3717_wo_miniapp.data.repositories

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.comp3717_wo_miniapp.data.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.DefenseStatEntity
import com.example.comp3717_wo_miniapp.data.EldenRingDatabase
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.ScalingStatsEntity
import com.example.comp3717_wo_miniapp.data.WEAPON
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.dataobjects.WeaponDao
import com.example.comp3717_wo_miniapp.data.models.Weapon
import com.example.comp3717_wo_miniapp.data.models.WeaponEntity
import com.example.comp3717_wo_miniapp.data.models.WeaponWithStats
import com.example.comp3717_wo_miniapp.data.models.Weapons
import com.example.comp3717_wo_miniapp.data.models.toWeapon
import com.example.comp3717_wo_miniapp.data.models.toWeaponEntity
import com.example.comp3717_wo_miniapp.data.models.toWeaponWithStats
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeaponRepository (
    override val eldenRingHttpClient:   HttpClient,
    val statDao:                        StatDao,
    val eldenWeaponDao:                 WeaponDao

) : EldenRingHttpRepository() {

    /**
     * Get all weapons.
     *
     * Optional, specify keywords
     * Optional, specify page of results
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Weapon> {
        val responseString = super.getItemsJson(WEAPON, searchTerms, page)
        val objects = Gson().fromJson(responseString, Weapons::class.java)

        println(objects)
        return objects.data // objects
    }

    /**
     * Inserts a new weapon to the database.
     */
    suspend fun saveItemToDatabase(weapon: Weapon) {
        val converted = weapon.toWeaponEntity()

        eldenWeaponDao.insertItem(converted)

        val attack = weapon.attack.map { AttackStatEntity(id = weapon.id, name = it.name, amount = it.amount) }
        val defence = weapon.defence.map { DefenseStatEntity(id = weapon.id, name = it.name, amount = it.amount) }
        val reqAt = weapon.reqAt.map { RequiredAttributeStatEntity(id = weapon.id, name = it.name, amount = it.amount) }
        val scalesWith = weapon.scalesWith.map { ScalingStatsEntity(id = weapon.id, name = it.name, scaling = it.scaling) }

        statDao.insertAttackStats(attack)
        statDao.insertDefenceStats(defence)
        statDao.insertReqAtStats(reqAt)
        statDao.insertScalingStats(scalesWith)
    }

    /**
     * Returns weapons from the database.
     */
    suspend fun getItemsFromDatabase(searchString: String?, page: Int): List<Weapon> {
        val dbRes = if (searchString.isNullOrEmpty()) {
            eldenWeaponDao.getItems(page)
        } else {
            eldenWeaponDao.getItems(searchString, page)
        }
        return dbRes.map {
            it.toWeapon()
        }
    }

    /**
     * Removes a weapon from the database.
     */
    suspend fun removeFromDatabase(weapon: Weapon) {
        eldenWeaponDao.deleteItem(weapon.toWeaponEntity())
    }

    /**
     * Get a specific weapon.
     */
    override suspend fun getItem(itemId: String): Weapon {
        TODO("Not yet implemented")
    }
}