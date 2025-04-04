package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.EldenRingDatabase
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.WEAPON
import com.example.comp3717_wo_miniapp.data.dataobjects.WeaponDao
import com.example.comp3717_wo_miniapp.data.models.Weapon
import com.example.comp3717_wo_miniapp.data.models.WeaponEntity
import com.example.comp3717_wo_miniapp.data.models.Weapons
import com.example.comp3717_wo_miniapp.data.models.toWeaponWithStats
import com.google.gson.Gson
import io.ktor.client.HttpClient

class WeaponRepository (
    override val eldenRingHttpClient:   HttpClient,
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
        val converted = weapon.toWeaponWithStats()
        eldenWeaponDao.insertItem(converted.first)
        eldenWeaponDao.insertNumericStatItems(converted.second.first)
        eldenWeaponDao.insertStringStatItems(converted.second.second)
    }

    /**
     * Returns weapons from the database.
     */
    suspend fun getItemsFromDatabase(): List<Weapon> {
        // return eldenWeaponDao.getItems()
        return emptyList()
    }

    /**
     * Get a specific weapon.
     */
    override suspend fun getItem(itemId: String): Weapon {
        TODO("Not yet implemented")
    }
}