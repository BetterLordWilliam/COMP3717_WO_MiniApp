package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.models.Incantation
import com.example.comp3717_wo_miniapp.data.models.Sorcery

@Entity(tableName = "er_sorceries")
data class SorceryEntity (
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

data class SorceryEntityWithStats (
    @Embedded val sorcery: SorceryEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val requires: List<RequiredAttributeStatEntity>
)

fun SorceryEntityWithStats.toIncantation(): Sorcery {
    return Sorcery(
        id = sorcery.id,
        name = sorcery.name,
        imageUrl = sorcery.imageUrl,
        description = sorcery.description,
        cost = sorcery.cost,
        type = sorcery.type,
        slots = sorcery.slots,
        effects = sorcery.effects,
        requires = requires
    )
}
