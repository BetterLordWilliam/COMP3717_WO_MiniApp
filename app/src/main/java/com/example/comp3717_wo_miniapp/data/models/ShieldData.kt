package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.NumericStatValue
import com.example.comp3717_wo_miniapp.data.StringStatValue
import com.google.gson.annotations.SerializedName

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
    @Ignore
    val attack:                 List<NumericStatValue>,
    @Ignore
    val defence:                List<NumericStatValue>,
    @Ignore
    @SerializedName("requiredAttributes")
    val reqAt:                  List<NumericStatValue>,
    @Ignore
    val scalesWith:             List<StringStatValue>
) : ItemData

data class Shields(
    override val data: List<Shield>
) : ItemGroup
