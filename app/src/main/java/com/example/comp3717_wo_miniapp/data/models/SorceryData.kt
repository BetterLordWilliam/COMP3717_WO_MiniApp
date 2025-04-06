package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.entites.IncantationEntity
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.entites.ItemGroup
import com.example.comp3717_wo_miniapp.data.entites.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.entites.SorceryEntity
import com.google.gson.annotations.SerializedName

data class Sorcery(
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val type:           String,
    val cost:           Int,
    val slots:          Int,
    val effects:        String,
    val requires:       List<RequiredAttributeStatEntity>?
) : ItemData

data class Sorceries(
    override val data: List<Sorcery>
) : ItemGroup

fun Sorcery.toSorceryEntity(): SorceryEntity {
    return SorceryEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        type = type,
        cost = cost,
        effects = effects,
        slots = slots
    )
}
