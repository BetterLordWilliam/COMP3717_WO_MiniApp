package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.entites.ArmourEntity
import com.example.comp3717_wo_miniapp.data.entites.ArmourWithStats
import com.example.comp3717_wo_miniapp.data.entites.DamageNegationStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.entites.ItemGroup
import com.example.comp3717_wo_miniapp.data.entites.ResistenceStatsEntity
import com.google.gson.annotations.SerializedName

data class Armour(
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:               String,
    val weight:                 Double,
    val dmgNegation:            List<DamageNegationStatsEntity>?,
    val resistance:             List<ResistenceStatsEntity>?
) : ItemData

data class Armours(
    override val data: List<Armour>
) : ItemGroup

fun Armour.toArmourEntity(): ArmourEntity {
    return ArmourEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        category = category,
        weight = weight
    )
}

fun Armour.toArmourWithStatsEntity(): ArmourWithStats {
    val armourEntity = toArmourEntity()

    dmgNegation?.forEach { it.parentId = id }
    resistance?.forEach { it.parentId = id }

    return ArmourWithStats(
        armour = armourEntity,
        dmgNegation = dmgNegation,
        resistance = resistance
    )
}