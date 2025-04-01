package com.example.comp3717_wo_miniapp.data.repositories

import com.example.comp3717_wo_miniapp.data.TALISMAN
import com.example.comp3717_wo_miniapp.data.Talisman
import com.example.comp3717_wo_miniapp.data.Talismans
import com.google.gson.Gson
import io.ktor.client.HttpClient

class TalismanRepository(
    override val eldenRingHttpClient: HttpClient

) : EldenRingHttpRepository<Talisman>() {

    /**
     * Fetch talismans from the ER endpoint
     * Optionally specify search terms
     * Optionally specify results page number
     */
    override suspend fun getItems(searchTerms: String?, page: Int): Talismans {
        val responseString = super.getItemsJson(TALISMAN, searchTerms, page)
        return Gson().fromJson(responseString, Talismans::class.java)
    }

    override suspend fun getItem(itemId: String): Talisman {
        TODO("Not yet implemented")
    }
}