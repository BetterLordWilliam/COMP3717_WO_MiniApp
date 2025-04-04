package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.NumericStatValue
import com.example.comp3717_wo_miniapp.data.StringStatValue
import com.example.comp3717_wo_miniapp.data.models.Weapon
import com.example.comp3717_wo_miniapp.data.models.WeaponEntity

@Dao
interface WeaponDao {

    @Query("SELECT * FROM er_weapons")
    suspend fun getItems(): List<WeaponEntity>

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumericStatItems(stats: List<NumericStatValue>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStringStatItems(stats: List<StringStatValue>)

    // @Transaction
    // @Query("SELECT * FROM er_weapons WHERE id = :weaponId")
    // suspend fun getWeaponWithStats(weaponId: String): WeaponWithAllStats?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(weapon: WeaponEntity)

}