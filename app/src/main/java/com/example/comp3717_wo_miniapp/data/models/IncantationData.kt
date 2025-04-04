package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.ItemData
import com.example.comp3717_wo_miniapp.data.ItemGroup
import com.example.comp3717_wo_miniapp.data.RequiredAttributeStatEntity
import com.google.gson.annotations.SerializedName

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
    val requires:       List<RequiredAttributeStatEntity>
) : ItemData

data class Incantations(
    override val data: List<Incantation>
) : ItemGroup
