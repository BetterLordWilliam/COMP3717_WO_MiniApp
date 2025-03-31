package com.example.comp3717_wo_miniapp.data

import com.google.gson.annotations.SerializedName


interface ItemData {
    val id:             String
    val name:           String
    val description:    String
    val imageUrl:       String
}

data class NumericStatValue (
    val name: String,
    val amount: Double
)

data class StringStatValue (
    val name: String,
    val scaling: String
)

data class Item (
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val type:           String,
    val effect:         String
) : ItemData

data class Weapon (
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val category:       String,
    val weight:         Double,
    val attack:         List<NumericStatValue>,
    val defence:        List<NumericStatValue>,
    @SerializedName("requiredAttributes")
    val reqAt:          List<NumericStatValue>,
    val scalesWith:     List<StringStatValue>
) : ItemData

data class Armour(
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val category:       String,
    val weight:         Double,
    val dmgNegation:    List<NumericStatValue>,
    val resistance:     List<NumericStatValue>
) : ItemData

data class Shield(
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val category:       String,
    val weight:         Double,
    val attack:         List<NumericStatValue>,
    val defence:        List<NumericStatValue>,
    @SerializedName("requiredAttributes")
    val reqAt:          List<NumericStatValue>,
    val scalesWith:     List<StringStatValue>
) : ItemData

data class Talisman(
    override val id:             String,
    override val name:           String,
    @SerializedName("image")
    override val imageUrl:       String,
    override val description:    String,
    val effects:        String
) : ItemData

data class Sorcery(
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

data class Incantation(
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
