package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.entites.ItemEntity
import com.example.comp3717_wo_miniapp.data.entites.TalismanEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Transaction
    @Query("SELECT * FROM er_items LIMIT 20 OFFSET :intPage*20")
    fun getItems(intPage: Int = 0): Flow<List<ItemEntity>>

    @Transaction
    @Query("SELECT * FROM er_items WHERE name LIKE '%' || :searchString || '%' LIMIT 20 OFFSET :intPage*20")
    fun getItems(searchString: String, intPage: Int = 0): Flow<List<ItemEntity>>

    @Delete
    suspend fun deleteItem(item: ItemEntity)

    @Insert
    suspend fun insertItem(item: ItemEntity)
}