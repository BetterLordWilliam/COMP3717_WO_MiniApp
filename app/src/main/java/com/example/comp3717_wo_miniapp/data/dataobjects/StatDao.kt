package com.example.comp3717_wo_miniapp.data.dataobjects

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.comp3717_wo_miniapp.data.entites.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.entites.DamageNegationStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.DefenceStatEntity
import com.example.comp3717_wo_miniapp.data.entites.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.entites.ResistenceStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.ScalingStatsEntity

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAttackStats(attackStatEntity: List<AttackStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReqAtStats(requiredAttributeStatEntity: List<RequiredAttributeStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDefenceStats(defenseStatEntity: List<DefenceStatEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResistanceStats(resistenceStatsEntity: List<ResistenceStatsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScalingStats(scalingStatsEntity: List<ScalingStatsEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDamageNegationStats(damageNegationStatsEntity: List<DamageNegationStatsEntity>)

}