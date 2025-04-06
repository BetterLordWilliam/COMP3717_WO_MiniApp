package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.entites.ShieldEntity
import com.example.comp3717_wo_miniapp.data.entites.ShieldWithStats
import kotlinx.coroutines.flow.Flow

@Dao
interface ShieldDao {

    @Transaction
    @Query("SELECT * FROM er_shields LIMIT 20 OFFSET :intPage*20")
    fun getItems(intPage: Int = 0): Flow<List<ShieldWithStats>>

    @Transaction
    @Query("SELECT * FROM er_shields WHERE name LIKE '%' || :searchString || '%' LIMIT 20 OFFSET :intPage*20")
    fun getItems(searchString: String, intPage: Int = 0): Flow<List<ShieldWithStats>>

    @Delete
    suspend fun deleteItem(shield: ShieldEntity)

    @Insert
    suspend fun insertItem(shield: ShieldEntity)
}
