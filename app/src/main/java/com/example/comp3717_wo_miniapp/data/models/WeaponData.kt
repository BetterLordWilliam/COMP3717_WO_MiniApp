package com.example.comp3717_wo_miniapp.data.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.NumericStatValue
import com.example.comp3717_wo_miniapp.data.StringStatValue
import com.google.gson.annotations.SerializedName

data class Weapon (
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:      String,
    val weight:        Double,
    val attack:                 List<NumericStatValue>,
    val defence:                List<NumericStatValue>,
    @SerializedName("requiredAttributes")
    val reqAt:                  List<NumericStatValue>,
    val scalesWith:             List<StringStatValue>
) : ItemData

data class Weapons(
    override val data: List<Weapon>
) : ItemGroup

fun Weapon.toWeaponWithStats(): Pair<WeaponEntity, Pair<List<NumericStatValue>, List<StringStatValue>>> {
    val weaponEntity = WeaponEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        category = category,
        weight = weight
    )

    val numericStats = attack.map { NumericStatValue(id = id, name = it.name, amount = it.amount) } +
            defence.map { NumericStatValue(id = id, name = it.name, amount = it.amount) } +
            reqAt.map { NumericStatValue(id = id, name = it.name, amount = it.amount) }

    val stringStats = scalesWith.map { StringStatValue(id = id, name = it.name, scaling = it.scaling) }

    println(numericStats)
    println(stringStats)

    return Pair(weaponEntity, Pair(numericStats, stringStats))
}

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

data class WeaponsWithStats(
    @Embedded val weapon: WeaponEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val attack: List<NumericStatValue>,
    val defence: List<NumericStatValue>,
    val reqAt: List<NumericStatValue>,
    val scalesWith: List<StringStatValue>
)


