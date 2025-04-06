package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// Room relationships
// https://developer.android.com/training/data-storage/room/relationships/one-to-many

// Using index to enforce uniqueness of stat items
// https://stackoverflow.com/questions/46790830/how-to-make-primary-key-auto-increment-while-using-composite-primary-keys-in-roo

interface ItemData {
    val id:             String
    val name:           String
    val description:    String
    val imageUrl:       String
}

interface ItemGroup {
    val data:           List<ItemData>
}

interface DoubleStat {
    val name:       String?
    val amount:     Double?
    var parentId:   String?
}

interface StringStat {
    val name:       String?
    val scaling:    String?
    var parentId:   String?
}

@Entity(
    tableName = "er_attack_stats",
    indices = [
        Index(value = ["parentId", "name", "amount"], unique = true)
    ])
data class AttackStatEntity (
    override val name:      String?,
    override val amount:    Double?,
    override var parentId:  String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : DoubleStat

@Entity(
    tableName = "er_req_stats",
    indices = [
        Index(value = ["parentId", "name", "amount"], unique = true)
    ])
data class RequiredAttributeStatEntity (
    override val name:      String?,
    override val amount:    Double?,
    override var parentId:  String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : DoubleStat

@Entity(
    tableName = "er_scaling_stats",
    indices = [
        Index(value = ["parentId", "name", "scaling"], unique = true)
    ])
data class ScalingStatsEntity (
    override val name:      String?,
    override val scaling:   String?,
    override var parentId:  String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : StringStat

@Entity(
    tableName = "er_dmg_negation_stats",
    indices = [
        Index(value = ["parentId", "name", "amount"], unique = true)
    ])
data class DamageNegationStatsEntity(
    override val name:      String?,
    override val amount:    Double?,
    override var parentId:  String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : DoubleStat

@Entity(
    tableName = "er_defense_stats",
    indices = [
        Index(value = ["parentId", "name", "amount"], unique = true),
    ])
data class DefenceStatEntity(
    override val name:      String?,
    override val amount:    Double?,
    override var parentId:  String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : DoubleStat

@Entity(
    tableName = "er_resistence_stats",
    indices = [
        Index(value = ["parentId", "name", "amount"], unique = true)
    ])
data class ResistenceStatsEntity(
    override val name:      String?,
    override val amount:    Double?,
    override var parentId:  String? = null,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : DoubleStat
