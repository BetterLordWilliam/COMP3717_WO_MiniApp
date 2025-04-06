package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.entites.TalismanEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponWithStats
import kotlinx.coroutines.flow.Flow

@Dao
interface TalismanDao {
    @Transaction
    @Query("SELECT * FROM er_talismans LIMIT 20 OFFSET :intPage*20")
    fun getItems(intPage: Int = 0): Flow<List<TalismanEntity>>

    @Transaction
    @Query("SELECT * FROM er_talismans WHERE name LIKE '%' || :searchString || '%' LIMIT 20 OFFSET :intPage*20")
    fun getItems(searchString: String, intPage: Int = 0): Flow<List<TalismanEntity>>

    @Delete
    suspend fun deleteItem(talisman: TalismanEntity)

    @Insert
    suspend fun insertItem(talisman: TalismanEntity)
}