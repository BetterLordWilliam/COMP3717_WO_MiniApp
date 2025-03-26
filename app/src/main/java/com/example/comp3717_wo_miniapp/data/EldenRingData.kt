package com.example.comp3717_wo_miniapp.data

import com.google.gson.annotations.SerializedName


data class NumericStatValue (
    val name: String,
    val amount: Int
)

data class StringStatValue (
    val name: String,
    val scaling: String
)

data class Item(
    val id:             String,
    val name:           String,
    @SerializedName("image")
    val imageUrl:       String,
    val description:    String,
    val type:           String,
    val effect:         String
)

data class Weapon (
    val id:             String,
    val name:           String,
    @SerializedName("image")
    val imageUrl:       String,
    val description:    String,
    val category:       String,
    val weight:         Double,
    val attack:         List<NumericStatValue>,
    val defence:        List<NumericStatValue>,
    @SerializedName("requiredAttributes")
    val reqAt:          List<NumericStatValue>,
    val scalesWith:     List<StringStatValue>
)

data class Armour(
    val id:             String,
    val name:           String,
    @SerializedName("image")
    val imageUrl:       String,
    val description:    String,
    val category:       String,
    val weight:         Double,
    val dmgNegation:    List<NumericStatValue>,
    val resistance:     List<NumericStatValue>
)

data class Shield(
    val id:             String,
    val name:           String,
    @SerializedName("image")
    val imageUrl:       String,
    val description:    String,
    val category:       String,
    val weight:         Double,
    val attack:         List<NumericStatValue>,
    val defence:        List<NumericStatValue>,
    @SerializedName("requiredAttributes")
    val reqAt:          List<NumericStatValue>,
    val scalesWith:     List<StringStatValue>
)

data class Talisman(
    val id:             String,
    val name:           String,
    @SerializedName("image")
    val imageUrl:       String,
    val description:    String,
    val effects:        String
)

data class Sorcery(
    val id:             String,
    val name:           String,
    @SerializedName("image")
    val imageUrl:       String,
    val description:    String,
    val type:           String,
    val cost:           Int,
    val slots:          Int,
    val effects:        String,
    val requires:       List<NumericStatValue>
)

data class Incantation(
    val id:             String,
    val name:           String,
    @SerializedName("image")
    val imageUrl:       String,
    val description:    String,
    val type:           String,
    val cost:           Int,
    val slots:          Int,
    val effects:        String,
    val requires:       List<NumericStatValue>
)
