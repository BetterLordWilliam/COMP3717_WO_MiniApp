package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.models.Incantation
import com.example.comp3717_wo_miniapp.data.models.Incantations
import com.google.gson.annotations.SerializedName

@Entity(tableName = "er_incantations")
data class IncantationEntity (
    @PrimaryKey
    val id:             String,
    val name:           String,
    val imageUrl:       String,
    val description:    String,
    val type:           String,
    val cost:           Int,
    val slots:          Int,
    val effects:        String,
)

data class IncantationEntityWithStats (
    @Embedded val incantation: IncantationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val requires: List<RequiredAttributeStatEntity>
)

fun IncantationEntityWithStats.toIncantation(): Incantation {
    return Incantation(
        id = incantation.id,
        name = incantation.name,
        imageUrl = incantation.imageUrl,
        description = incantation.description,
        cost = incantation.cost,
        type = incantation.type,
        slots = incantation.slots,
        effects = incantation.effects,
        requires = requires
    )
}
