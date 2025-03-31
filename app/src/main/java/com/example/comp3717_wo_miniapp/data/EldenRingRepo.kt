package com.example.comp3717_wo_miniapp.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class EldenRingRepo(
    private val eldenRingHttpClient: HttpClient
) {

//    inline fun <reified T> String.
//
//    suspend fun <T> getGeneric(endpointString : String, searchTerms: String? = null, page : Int = 0) : T {
//        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(endpointString, searchTerms, page))
//        val json = response.body<JsonObject>().toString()
//        return Gson().fromJson(json, T::class.java)
//    }

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
     * fetches Elden Ring items from the Elden Ring item API endpoint.
     */
    suspend fun getItems(searchTerms: String? = null, page : Int = 0) : Items {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(ITEM, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Items::class.java)
    }

    /**
     * fetches incantations from the Elden Ring incantations API endpoint.
     */
    suspend fun getIncantations(searchTerms: String? = null, page : Int = 0) : Incantations {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(INCANTATION, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Incantations::class.java)
    }

    /**
     * fetches sorceries from the Elden Ring sorceries API endpoint.
     */
    suspend fun getSorceries(searchTerms : String? = null, page : Int = 0) : Sorceries {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(SORCERY, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Sorceries::class.java)
    }

    /**
     * fetches talismans from the Elden Ring talismans API endpoint.
     */
    suspend fun getTalismans(searchTerms : String? = null, page : Int = 0) : Talismans {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(TALISMAN, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Talismans::class.java)
    }

    /**
     * fetches shields from the Elden Ring shields API endpoint.
     */
    suspend fun getShields(searchTerms : String? = null, page : Int = 0) : Shields {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(SHIELD, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        return Gson().fromJson(json, Shields::class.java)
    }
}