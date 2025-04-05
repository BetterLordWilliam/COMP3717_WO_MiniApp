package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.entites.WeaponEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponWithStats
import kotlinx.coroutines.flow.Flow

// SQLlite string cat
// https://stackoverflow.com/questions/44184769/android-room-select-query-with-like

// Room database flow -- subscribe to database updates
//

@Dao
interface WeaponDao {

    @Transaction
    @Query("SELECT * FROM er_weapons LIMIT 20 OFFSET :intPage*20")
    fun getItems(intPage: Int = 0): Flow<List<WeaponWithStats>>

    @Transaction
    @Query("SELECT * FROM er_weapons WHERE name LIKE '%' || :searchString || '%' LIMIT 20 OFFSET :intPage*20")
    fun getItems(searchString: String, intPage: Int = 0): Flow<List<WeaponWithStats>>

    @Delete
    suspend fun deleteItem(weapon: WeaponEntity)

    @Insert
    suspend fun insertItem(weapon: WeaponEntity)
}
