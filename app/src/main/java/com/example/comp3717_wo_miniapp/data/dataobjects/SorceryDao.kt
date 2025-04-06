package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.entites.IncantationEntity
import com.example.comp3717_wo_miniapp.data.entites.IncantationEntityWithStats
import com.example.comp3717_wo_miniapp.data.entites.SorceryEntity
import com.example.comp3717_wo_miniapp.data.entites.SorceryEntityWithStats
import kotlinx.coroutines.flow.Flow

@Dao
interface SorceryDao {

    @Transaction
    @Query("SELECT * FROM er_sorceries LIMIT 20 OFFSET :intPage*20")
    fun getItems(intPage: Int = 0): Flow<List<SorceryEntityWithStats>>

    @Transaction
    @Query("SELECT * FROM er_sorceries WHERE name LIKE '%' || :searchString || '%' LIMIT 20 OFFSET :intPage*20")
    fun getItems(searchString: String, intPage: Int = 0): Flow<List<SorceryEntityWithStats>>

    @Delete
    suspend fun deleteItem(sorcery: SorceryEntity)

    @Insert
    suspend fun insertItem(sorcery: SorceryEntity)

}