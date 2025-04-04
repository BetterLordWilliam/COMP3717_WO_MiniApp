package com.example.comp3717_wo_miniapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

// Room relationships
// https://developer.android.com/training/data-storage/room/relationships/one-to-many

// Some useful information about ignoring certain fields in Room dataclass
// https://stackoverflow.com/questions/67270176/how-to-make-room-ignore-a-field-in-the-data-model

// Some useful information about ignoring certain fields in Gson data class
// https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/annotations/Expose.html

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
    val name:       String
    val amount:     Double
}

interface StringStat {
    val name:       String
    val scaling: String
}

@Entity(tableName = "er_attack_stats", primaryKeys = ["id", "name", "amount"])
data class AttackStatEntity (
    val id:             String,
    override val name:           String,
    override val amount:         Double
) : DoubleStat

@Entity(tableName = "er_def_stats", primaryKeys = ["id", "name", "amount"])
data class DefenseStatEntity (
    val id:             String,
    override val name:           String,
    override val amount:         Double
) : DoubleStat

@Entity(tableName = "er_req_stats", primaryKeys = ["id", "name", "amount"])
data class RequiredAttributeStatEntity (
    val id:             String,
    override val name:           String,
    override val amount:         Double
) : DoubleStat

@Entity(tableName = "er_scaling_stats", primaryKeys = ["id", "name", "scaling"])
data class ScalingStatsEntity (
    val id:             String,
    override val name:           String,
    override val scaling:     String
) : StringStat

@Entity(tableName = "er_dmg_negation_stats", primaryKeys = ["id", "name", "amount"])
data class DamageNegationStatsEntity(
    val id:             String,
    override val name:           String,
    override val amount:         Double
) : DoubleStat

@Entity(tableName = "er_resistence_stats", primaryKeys = ["id", "name", "amount"])
data class ResistenceStatsEntity(
    val id:             String,
    override val name:           String,
    override val amount:         Double
) : DoubleStat

//fun DoubleStat.toPair(): Pair<String, Double> = Pair(name, amount)
//fun List<DoubleStat>.toPairList(): List<Pair<String, Double>> = map { it.toPair() }
//
//fun StringStat.toPair(): Pair<String, String> = Pair(name, scalesWith)
//fun List<StringStat>.toPairList(): List<Pair<String, String>> = map { it.toPair() }
