package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.entites.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.entites.DefenceStatEntity
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.entites.ItemGroup
import com.example.comp3717_wo_miniapp.data.entites.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.entites.ScalingStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.ShieldEntity
import com.example.comp3717_wo_miniapp.data.entites.ShieldWithStats
import com.google.gson.annotations.SerializedName

data class Shield(
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:               String,
    val weight:                 Double,
    val attack:                 List<AttackStatEntity>?,
    val defence:                List<DefenceStatEntity>?,
    @SerializedName("requiredAttributes")
    val reqAt:                  List<RequiredAttributeStatEntity>?,
    val scalesWith:             List<ScalingStatsEntity>?
) : ItemData

data class Shields(
    override val data: List<Shield>
) : ItemGroup

fun Shield.toShieldEntity(): ShieldEntity {
    return ShieldEntity(
        id = id,
        name = name,
        description = description,
        imageUrl = imageUrl,
        category = category,
        weight = weight
    )
}

fun Shield.toShieldEntityWithStats(): ShieldWithStats {
    val shieldEntity = toShieldEntity()

    attack?.forEach { it.parentId = id }
    defence?.forEach { it.parentId = id }
    reqAt?.forEach { it.parentId = id }
    scalesWith?.forEach { it.parentId = id }

    return ShieldWithStats(
        shield = shieldEntity,
        attack = attack,
        defence = defence,
        reqAt = reqAt,
        scalesWith = scalesWith
    )
}
