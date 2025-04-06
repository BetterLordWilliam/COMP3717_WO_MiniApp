package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.entites.IncantationEntity
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.entites.ItemGroup
import com.example.comp3717_wo_miniapp.data.entites.RequiredAttributeStatEntity
import com.google.gson.annotations.SerializedName

data class Incantation(
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

data class Incantations(
    override val data: List<Incantation>
) : ItemGroup

fun Incantation.toIncantationEntity(): IncantationEntity {
    return IncantationEntity(
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
