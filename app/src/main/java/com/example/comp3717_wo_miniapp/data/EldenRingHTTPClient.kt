package com.example.comp3717_wo_miniapp.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.gson.gson

// Define the API top-level endpoint
const val ENDPOINT = "https://eldenring.fanapis.com/api"

// Define API routes
const val ITEM                      = "$ENDPOINT/items"
const val WEAPON                    = "$ENDPOINT/weapons"
const val ARMOUR                    = "$ENDPOINT/armors"
const val SHIELD                    = "$ENDPOINT/shields"
const val TALISMAN                  = "$ENDPOINT/talismans"
const val SORCERY                   = "$ENDPOINT/sorceries"
const val INCANTATION               = "$ENDPOINT/incantations"

// Define specific query template strings
const val FETCH_BY_ID               = "%s/%s"
const val NAME_SEARCH               = "%s?name=%s"
const val NAME_SEARCH_PAG           = "%s?name=%s&page=%d"
const val TYPE_PAG                  = "%s?page=%d"
// const val NAME_SEARCH_PAG           = "%s?name=%s&limit=%d&page=%d"

// Initialize the HTTP client
val eldenRingHttpClient = HttpClient {
    install(ContentNegotiation) {
        gson()
    }
}
