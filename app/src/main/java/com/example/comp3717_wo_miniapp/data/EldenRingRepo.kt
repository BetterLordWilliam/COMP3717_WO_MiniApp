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
    suspend fun getWeapons(searchTerms: String? = null, page : Int = 0) : Weapons {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(WEAPON, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Weapons::class.java)
    }

    /**
     * fetches weapons from the Elden Ring API endpoint.
     */
    suspend fun getArmours(searchTerms: String? = null, page : Int = 0) : Armours {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(ARMOUR, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Armours::class.java)
    }

    /**
     * fetches weapons from the Elden Ring API endpoint by search terms.
     */
    suspend fun getWeaponsFromSearchTerms(searchTerms: String, page : Int = 0) : Weapons {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(WEAPON, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Weapons::class.java)
    }

    /**
     * fetches armours from the Elden Ring API endpoint.
     */
    suspend fun getAllArmours(page : Int = 0) : Armours {
        val response = eldenRingHttpClient.get(TYPE_PAG.format(ARMOUR, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Armours::class.java)
    }

    /**
     * fetches armours from the Elden Ring API endpoint.
     */
    suspend fun getArmoursFromSearchTerms(searchTerms: String) : Armours {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(ARMOUR, searchTerms, 0))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Armours::class.java)
    }
}