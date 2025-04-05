package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.entites.DefenceStatEntity
import com.example.comp3717_wo_miniapp.data.models.Weapon

@Entity(tableName = "er_weapons")
data class WeaponEntity (
    @PrimaryKey
    val id:            String,
    val name:          String,
    val imageUrl:      String,
    val description:   String,
    val category:      String,
    val weight:        Double,
)

data class WeaponWithStats(
    @Embedded val weapon: WeaponEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId",
        entity = AttackStatEntity::class,
    )
    val attack: List<AttackStatEntity>? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId",
        entity = DefenceStatEntity::class
    )
    val defence: List<DefenceStatEntity>? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId",
        entity = RequiredAttributeStatEntity::class
    )
    val reqAt: List<RequiredAttributeStatEntity>? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId",
        entity = ScalingStatsEntity::class
    )
    val scalesWith: List<ScalingStatsEntity>? = null
)

fun WeaponWithStats.toWeapon(): Weapon {
    return Weapon(
        id = weapon.id,
        name = weapon.name,
        imageUrl = weapon.imageUrl,
        description = weapon.description,
        category = weapon.category,
        weight = weapon.weight,
        attack = attack,
        defence = defence,
        reqAt = reqAt,
        scalesWith = scalesWith
    )
}
