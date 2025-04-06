package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.entites.ItemGroup
import com.example.comp3717_wo_miniapp.data.entites.SorceryEntity
import com.example.comp3717_wo_miniapp.data.entites.TalismanEntity
import com.google.gson.annotations.SerializedName


data class Talisman(
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val effects:        String?
) : ItemData

data class Talismans(
    override val data : List<Talisman>
) : ItemGroup

fun Talisman.toTalismanEntity(): TalismanEntity {
    return TalismanEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        effects = effects,
    )
}
