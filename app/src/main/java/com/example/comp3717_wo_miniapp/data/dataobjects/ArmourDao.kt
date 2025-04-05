package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.entites.ArmourEntity
import com.example.comp3717_wo_miniapp.data.entites.ArmourWithStats
import com.example.comp3717_wo_miniapp.data.entites.WeaponEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArmourDao {
    @Transaction
    @Query("SELECT * FROM er_armour LIMIT 20 OFFSET :intPage*20")
    fun getItems(intPage: Int = 0): Flow<List<ArmourWithStats>>

    @Transaction
    @Query("SELECT * FROM er_armour WHERE name LIKE '%' || :searchString || '%' LIMIT 20 OFFSET :intPage*20")
    fun getItems(searchString: String, intPage: Int = 0): Flow<List<ArmourWithStats>>

    @Delete
    suspend fun deleteItem(armour: ArmourEntity)

    @Insert
    suspend fun insertItem(armour: ArmourEntity)
}