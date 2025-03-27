package com.example.comp3717_wo_miniapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EldenRingRepo(
    private val eldenRingHttpClient: HttpClient
) {

    /**
     * fetches weapons from the Elden Ring API endpoint.
     */
    suspend fun getAllWeapons(): Weapons {
        val response = eldenRingHttpClient.get(WEAPON)
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Weapons::class.java)
    }

    /**
     * fetches armours from the Elden Ring API endpoint.
     */
    suspend fun getAllArmours(): Armours {
        val response = eldenRingHttpClient.get(ARMOUR)
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Armours::class.java)
    }
}