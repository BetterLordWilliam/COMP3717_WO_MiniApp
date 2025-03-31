package com.example.comp3717_wo_miniapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


interface ItemData {
    val id:             String
    val name:           String
    val description:    String
    val imageUrl:       String
}

@Entity(tableName = "er_number_stats")
data class NumericStatValue (
    val name:       String,
    val amount:     Double
)

@Entity(tableName = "er_string_stats")
data class StringStatValue (
    val name:       String,
    val scaling:    String
)

@Entity(tableName = "er_items")
data class Item (
    @PrimaryKey
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val type:                   String,
    val effect:                 String
) : ItemData

@Entity(tableName = "er_weapons")
data class Weapon (
    @PrimaryKey
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:               String,
    val weight:                 Double,
    val attack:                 List<NumericStatValue>,
    val defence:                List<NumericStatValue>,
    @SerializedName("requiredAttributes")
    val reqAt:                  List<NumericStatValue>,
    val scalesWith:             List<StringStatValue>
) : ItemData

@Entity(tableName = "er_armours")
data class Armour(
    @PrimaryKey
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:               String,
    val weight:                 Double,
    val dmgNegation:            List<NumericStatValue>,
    val resistance:             List<NumericStatValue>
) : ItemData

@Entity(tableName = "er_shields")
data class Shield(
    @PrimaryKey
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:               String,
    val weight:                 Double,
    val attack:                 List<NumericStatValue>,
    val defence:                List<NumericStatValue>,
    @SerializedName("requiredAttributes")
    val reqAt:                  List<NumericStatValue>,
    val scalesWith:             List<StringStatValue>
) : ItemData

@Entity(tableName = "er_talismans")
data class Talisman(
    @PrimaryKey
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val effects:        String
) : ItemData

@Entity(tableName = "er_sorceries")
data class Sorcery(
    @PrimaryKey
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val type:           String,
    val cost:           Int,
    val slots:          Int,
    val effects:        String,
    val requires:       List<NumericStatValue>
) : ItemData

@Entity(tableName = "er_incantations")
data class Incantation(
    @PrimaryKey
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val type:           String,
    val cost:           Int,
    val slots:          Int,
    val effects:        String,
    val requires:       List<NumericStatValue>
) : ItemData

data class Items(
    val data:           List<Item>
)

data class Weapons(
    val data:           List<Weapon>
)

data class Armours(
    val data:           List<Armour>
)

data class Shields(
    val data:           List<Shield>
)

data class Talismans(
    val data:           List<Talisman>
)

data class Sorceries(
    val data:           List<Sorcery>
)

data class Incantations(
    val data:           List<Incantation>
)
