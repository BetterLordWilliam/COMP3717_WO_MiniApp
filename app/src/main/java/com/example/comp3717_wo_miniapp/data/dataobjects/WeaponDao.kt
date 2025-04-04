package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.ScalingStatsEntity
import com.example.comp3717_wo_miniapp.data.models.Weapon
import com.example.comp3717_wo_miniapp.data.models.WeaponEntity
import com.example.comp3717_wo_miniapp.data.models.WeaponWithStats

@Dao
interface WeaponDao {

//    @Query("SELECT * FROM er_weapons")
//    suspend fun getItems(): List<WeaponEntity>

    @Transaction
    @Query("SELECT * FROM er_weapons")
    suspend fun getItems(): List<WeaponWithStats>

//    @Transaction
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertWeaponWithStats(weaponWithStats: WeaponWithStats)

    // @Transaction
    // @Query("SELECT * FROM er_weapons WHERE id = :weaponId")
    // suspend fun getWeaponWithStats(weaponId: String): WeaponWithAllStats?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(weapon: WeaponEntity)
}