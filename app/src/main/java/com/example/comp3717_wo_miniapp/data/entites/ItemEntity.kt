package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.models.Item
import com.example.comp3717_wo_miniapp.data.models.Talisman

@Entity(tableName = "er_items")
data class ItemEntity (
    @PrimaryKey
    val id:             String,
    val name:           String,
    val imageUrl:       String,
    val description:    String,
    val type:           String?,
    val effect:         String?,
)

fun ItemEntity.toItem(): Item {
    return Item(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        type = type,
        effect = effect,
    )
}