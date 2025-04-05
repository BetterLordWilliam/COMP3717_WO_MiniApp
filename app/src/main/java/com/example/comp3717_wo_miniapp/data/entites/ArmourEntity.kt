package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.models.Armour
import com.google.gson.annotations.SerializedName

@Entity(tableName = "er_armour")
data class ArmourEntity(
    @PrimaryKey
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:               String,
    val weight:                 Double,
) : ItemData

data class ArmourWithStats(
    @Embedded
    val armour:     ArmourEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val dmgNegation:            List<DamageNegationStatsEntity>? = null,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val resistance:  List<ResistenceStatsEntity>? = null
)

fun ArmourWithStats.toArmour(): Armour {
    return Armour(
        id = armour.id,
        name = armour.name,
        imageUrl = armour.imageUrl,
        description = armour.description,
        category = armour.category,
        weight = armour.weight,
        resistance = resistance,
        dmgNegation = dmgNegation
    )
}
