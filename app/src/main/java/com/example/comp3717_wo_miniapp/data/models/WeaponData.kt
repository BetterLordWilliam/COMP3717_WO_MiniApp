package com.example.comp3717_wo_miniapp.data.models

import com.example.comp3717_wo_miniapp.data.entites.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.entites.DefenceStatEntity
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.entites.ItemGroup
import com.example.comp3717_wo_miniapp.data.entites.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.entites.ScalingStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponWithStats
import com.google.gson.annotations.SerializedName

// Helpful stack overflow regarding proper setup of room relations
// https://stackoverflow.com/questions/44330452/android-persistence-room-cannot-figure-out-how-to-read-this-field-from-a-curso

data class Weapon (
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:      String,
    val weight:        Double,
    val attack:                 List<AttackStatEntity>?,
    val defence:                List<DefenceStatEntity>?,
    @SerializedName("requiredAttributes")
    val reqAt:                  List<RequiredAttributeStatEntity>?,
    val scalesWith:             List<ScalingStatsEntity>?
) : ItemData

data class Weapons(
    override val data: List<Weapon>
) : ItemGroup

fun Weapon.toWeaponEntity(): WeaponEntity {
    return WeaponEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        category = category,
        weight = weight
    )
}

fun Weapon.toWeaponWithStats(): WeaponWithStats {
    val weaponEntity = toWeaponEntity()

    // Save refernces the the weapon parent entity
    attack?.forEach { it.parentId = id }
    defence?.forEach { it.parentId = id }
    reqAt?.forEach { it.parentId = id }
    scalesWith?.forEach { it.parentId = id }

    return WeaponWithStats(
        weapon = weaponEntity,
        attack = attack,
        defence = defence,
        reqAt = reqAt,
        scalesWith = scalesWith
    )
}
