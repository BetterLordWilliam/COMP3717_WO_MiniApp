package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.NAME_SEARCH_PAG
import com.google.gson.JsonObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

abstract class EldenRingHttpRepository {

    abstract val eldenRingHttpClient: HttpClient

    /**
     * Http calls are the same so just return the string and convert in repo (i guess) 🤷‍♂️🤷‍♂️
     *
     * @return {String} JSON string
     */
    suspend fun getItemsJson(
        endpointString :    String,
        searchTerms :       String? = "",
        page :              Int     = 0
    ) : String {
        val response = eldenRingHttpClient.get(NAME_SEARCH_PAG.format(endpointString, searchTerms, page))
        val json = response.body<JsonObject>().toString()
        println(json)
        println(NAME_SEARCH_PAG.format(endpointString, searchTerms, page))
        return json
    }

    abstract suspend fun getItems(searchTerms : String? = "", page : Int = 0) : List<ItemData>
    abstract suspend fun getItem(itemId : String) : ItemData

}