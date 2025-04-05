package com.example.comp3717_wo_miniapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comp3717_wo_miniapp.data.dataobjects.ArmourDao
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.dataobjects.WeaponDao
import com.example.comp3717_wo_miniapp.data.entites.ArmourEntity
import com.example.comp3717_wo_miniapp.data.entites.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.entites.DamageNegationStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.entites.ResistenceStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.ScalingStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponEntity
import com.example.comp3717_wo_miniapp.data.entites.DefenceStatEntity

@Database(entities = [
    AttackStatEntity::class,
    DefenceStatEntity::class,
    RequiredAttributeStatEntity::class,
    ScalingStatsEntity::class,
    DamageNegationStatsEntity::class,
    ResistenceStatsEntity::class,
    WeaponEntity::class,
    ArmourEntity::class
 ], version = 10)
abstract class EldenRingDatabase : RoomDatabase() {
    abstract fun weaponDao(): WeaponDao
    abstract fun statDao(): StatDao
    abstract fun armourDao(): ArmourDao
}

/**
 * Database singleton
 */
object EldenRingDB {
    fun getDatabase(context: Context): EldenRingDatabase {
        return Room.databaseBuilder(
            context,
            EldenRingDatabase::class.java,
            "er_database"
        )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}