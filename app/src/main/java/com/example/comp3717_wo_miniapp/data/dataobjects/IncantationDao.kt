package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.entites.IncantationEntity
import com.example.comp3717_wo_miniapp.data.entites.IncantationEntityWithStats
import com.example.comp3717_wo_miniapp.data.entites.ShieldEntity
import com.example.comp3717_wo_miniapp.data.entites.ShieldWithStats
import kotlinx.coroutines.flow.Flow

@Dao
interface IncantationDao {

    @Transaction
    @Query("SELECT * FROM er_incantations LIMIT 20 OFFSET :intPage*20")
    fun getItems(intPage: Int = 0): Flow<List<IncantationEntityWithStats>>

    @Transaction
    @Query("SELECT * FROM er_incantations WHERE name LIKE '%' || :searchString || '%' LIMIT 20 OFFSET :intPage*20")
    fun getItems(searchString: String, intPage: Int = 0): Flow<List<IncantationEntityWithStats>>

    @Delete
    suspend fun deleteItem(incantation: IncantationEntity)

    @Insert
    suspend fun insertItem(incantation: IncantationEntity)

}