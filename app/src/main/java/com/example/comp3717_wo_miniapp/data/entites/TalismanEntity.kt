package com.example.comp3717_wo_miniapp.data.entites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.comp3717_wo_miniapp.data.models.Sorcery
import com.example.comp3717_wo_miniapp.data.models.Talisman

@Entity(tableName = "er_talismans")
data class TalismanEntity (
    @PrimaryKey
    val id:             String,
    val name:           String,
    val imageUrl:       String,
    val description:    String,
    val effects:        String?,
)

fun TalismanEntity.toTalisman(): Talisman {
    return Talisman(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        effects = effects,
    )
}
