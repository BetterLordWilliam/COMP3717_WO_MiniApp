package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.WEAPON
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.dataobjects.WeaponDao
import com.example.comp3717_wo_miniapp.data.entites.WeaponEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponWithStats
import com.example.comp3717_wo_miniapp.data.models.Weapon
import com.example.comp3717_wo_miniapp.data.models.Weapons
import com.example.comp3717_wo_miniapp.data.models.toWeaponEntity
import com.example.comp3717_wo_miniapp.data.entites.toWeapon
import com.google.gson.Gson
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeaponRepository (
    override val eldenRingHttpClient:   HttpClient,
    val statDao:                        StatDao,
    val eldenWeaponDao:                 WeaponDao

) : EldenRingItemRepository<Weapon>() {

    /**
     * Get all weapons.
     *
     * Optional, specify keywords
     * Optional, specify page of results
     */
    override suspend fun getItems(searchTerms: String?, page: Int): List<Weapon> {
        val responseString = super.getItemsJson(WEAPON, searchTerms, page)
        val objects = Gson().fromJson(responseString, Weapons::class.java)

        // println(objects)
        return objects.data // objects
    }

    /**
     * Inserts a new weapon to the database.
     */
    override suspend fun saveItemToDatabase(item: Weapon) {
        val weaponEntity = WeaponEntity(
            id = item.id,
            imageUrl = item.imageUrl,
            name = item.name,
            description = item.description,
            category = item.category,
            weight = item.weight
        )

        eldenWeaponDao.insertItem(weaponEntity)

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

    /**
     * Returns weapons from the database.
     */
    override fun getItemsFromDatabase(searchString: String?, page: Int): Flow<List<Weapon>> {
        val dbRes = if (searchString.isNullOrEmpty()) {
            eldenWeaponDao.getItems(page)
        } else {
            eldenWeaponDao.getItems(searchString, page)
        }
        return dbRes.map {
            it.map { weaponWithStats ->
                Weapon(
                    id = weaponWithStats.weapon.id,
                    name = weaponWithStats.weapon.name,
                    imageUrl = weaponWithStats.weapon.imageUrl,
                    description = weaponWithStats.weapon.description,
                    category = weaponWithStats.weapon.category,
                    weight = weaponWithStats.weapon.weight,
                    attack = weaponWithStats.attack,
                    defence = weaponWithStats.defence,
                    reqAt = weaponWithStats.reqAt,
                    scalesWith = weaponWithStats.scalesWith
                )
            }
        }
    }

    /**
     * Removes a weapon from the database.
     */
    override suspend fun removeItemFromDatabase(item: Weapon) {
        eldenWeaponDao.deleteItem(item.toWeaponEntity())
    }
}