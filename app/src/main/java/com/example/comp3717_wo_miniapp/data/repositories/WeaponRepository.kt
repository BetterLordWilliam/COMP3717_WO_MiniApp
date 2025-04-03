package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.WEAPON
import com.example.comp3717_wo_miniapp.data.Weapon
import com.example.comp3717_wo_miniapp.data.Weapons
import com.google.gson.Gson
import io.ktor.client.HttpClient

class WeaponRepository (
    override val eldenRingHttpClient: HttpClient

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
     * Get a specific weapon.
     */
    override suspend fun getItem(itemId: String): Weapon {
        TODO("Not yet implemented")
    }
}