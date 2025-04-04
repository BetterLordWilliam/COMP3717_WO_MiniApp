package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.example.comp3717_wo_miniapp.data.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.DefenseStatEntity
import com.example.comp3717_wo_miniapp.data.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.ResistenceStatsEntity
import com.example.comp3717_wo_miniapp.data.ScalingStatsEntity

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttackStats(attackStatEntity: List<AttackStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReqAtStats(requiredAttributeStatEntity: List<RequiredAttributeStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefenceStats(defenseStatEntity: List<DefenseStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResistanceStats(resistenceStatsEntity: List<ResistenceStatsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScalingStats(scalingStatsEntity: List<ScalingStatsEntity>)

}