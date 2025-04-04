package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.DefenseStatEntity
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.ScalingStatsEntity
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
    val attack:                 List<AttackStatEntity>,
    val defence:                List<DefenseStatEntity>,
    @SerializedName("requiredAttributes")
    val reqAt:                  List<RequiredAttributeStatEntity>,
    val scalesWith:             List<ScalingStatsEntity>
) : ItemData

data class Shields(
    override val data: List<Shield>
) : ItemGroup
