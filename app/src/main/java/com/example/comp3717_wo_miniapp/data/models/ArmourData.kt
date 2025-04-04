package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.NumericStatValue
import com.google.gson.annotations.SerializedName


data class Armour(
    @PrimaryKey
    override val id:            String,
    override val name:          String,
    @SerializedName("image")
    override val imageUrl:      String,
    override val description:   String,
    val category:               String,
    val weight:                 Double,
    @Ignore
    val dmgNegation:            List<NumericStatValue>,
    @Ignore
    val resistance:             List<NumericStatValue>
) : ItemData

data class Armours(
    override val data: List<Armour>
) : ItemGroup

