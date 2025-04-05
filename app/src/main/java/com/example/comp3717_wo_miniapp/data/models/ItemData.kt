package com.example.comp3717_wo_miniapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.comp3717_wo_miniapp.data.entites.ItemData
import com.example.comp3717_wo_miniapp.data.entites.ItemGroup
import com.google.gson.annotations.SerializedName

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

data class Items(
    override val data: List<Item>
) : ItemGroup
