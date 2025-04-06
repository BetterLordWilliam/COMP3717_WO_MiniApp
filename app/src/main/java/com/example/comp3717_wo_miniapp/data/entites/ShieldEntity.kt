package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.models.Shield

@Entity(tableName = "er_shields")
data class ShieldEntity (
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
    val description: String,
    val category: String,
    val weight: Double
)

data class ShieldWithStats(
    @Embedded
    val shield:     ShieldEntity,
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

fun ShieldWithStats.toShield(): Shield {
    return Shield(
        id = shield.id,
        name = shield.name,
        imageUrl = shield.imageUrl,
        description = shield.description,
        category = shield.category,
        weight = shield.weight,
        attack = attack,
        defence = defence,
        reqAt = reqAt,
        scalesWith = scalesWith
    )
}
