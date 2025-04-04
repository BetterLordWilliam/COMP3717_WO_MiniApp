package com.example.comp3717_wo_miniapp.data

import android.content.Context
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Room relationships
// https://developer.android.com/training/data-storage/room/relationships/one-to-many

// Some useful information about ignoring certain fields in Room dataclass
// https://stackoverflow.com/questions/67270176/how-to-make-room-ignore-a-field-in-the-data-model

// Some useful information about ignoring certain fields in Gson data class
// https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/annotations/Expose.html

interface ItemData {
    val id:             String
    val name:           String
    val description:    String
    val imageUrl:       String
}

interface ItemGroup {
    val data:           List<ItemData>
}

@Entity(tableName = "er_number_stats", primaryKeys = ["id", "name", "amount"])
data class NumericStatValue (
    val id:             String,         // Same ID as the ItemData
    val name:           String,
    val amount:         Double
)

@Entity(tableName = "er_string_stats", primaryKeys = ["id", "name", "scaling"])
data class StringStatValue (
    val id:             String,         // Same ID as the ItemData
    val name:           String,
    val scaling:        String
)
