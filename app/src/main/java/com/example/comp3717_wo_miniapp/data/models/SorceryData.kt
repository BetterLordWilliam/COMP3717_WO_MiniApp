package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.NumericStatValue
import com.google.gson.annotations.SerializedName

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
    @Ignore
    val requires:       List<NumericStatValue>
) : ItemData

data class Sorceries(
    override val data: List<Sorcery>
) : ItemGroup
