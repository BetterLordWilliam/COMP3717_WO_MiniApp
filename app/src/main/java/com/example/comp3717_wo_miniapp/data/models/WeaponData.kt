package com.example.comp3717_wo_miniapp.data.models

import android.text.style.ScaleXSpan
import androidx.compose.ui.platform.isDebugInspectorInfoEnabled
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.DefenseStatEntity
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.ScalingStatsEntity
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Required

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
    val attack:                 List<AttackStatEntity>,
    val defence:                List<DefenseStatEntity>,
    @SerializedName("requiredAttributes")
    val reqAt:                  List<RequiredAttributeStatEntity>,
    val scalesWith:             List<ScalingStatsEntity>
) : ItemData

data class Weapons(
    override val data: List<Weapon>
) : ItemGroup

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
        entityColumn = "id",
        entity = AttackStatEntity::class,
    )
    val attack: List<AttackStatEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = DefenseStatEntity::class
    )
    val defence: List<DefenseStatEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = RequiredAttributeStatEntity::class
    )
    val reqAt: List<RequiredAttributeStatEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = ScalingStatsEntity::class
    )
    val scalesWith: List<ScalingStatsEntity>
)

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
    val attack = attack.map { AttackStatEntity(id = id, name = it.name, amount = it.amount) }
    val defence = defence.map { DefenseStatEntity(id = id, name = it.name, amount = it.amount) }
    val reqAt = reqAt.map { RequiredAttributeStatEntity(id = id, name = it.name, amount = it.amount) }
    val scalesWith = scalesWith.map { ScalingStatsEntity(id = id, name = it.name, scaling = it.scaling) }

    return WeaponWithStats(
        weapon = weaponEntity,
        attack = attack,
        defence = defence,
        reqAt = reqAt,
        scalesWith = scalesWith
    )
}

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
