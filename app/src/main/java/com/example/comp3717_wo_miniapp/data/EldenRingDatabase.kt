package com.example.comp3717_wo_miniapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.dataobjects.WeaponDao
import com.example.comp3717_wo_miniapp.data.models.WeaponEntity
import kotlinx.serialization.Required

@Database(entities = [
    AttackStatEntity::class,
    DefenseStatEntity::class,
    RequiredAttributeStatEntity::class,
    ScalingStatsEntity::class,
    DamageNegationStatsEntity::class,
    ResistenceStatsEntity::class,
    WeaponEntity::class,
 ], version = 5)
abstract class EldenRingDatabase : RoomDatabase() {
    abstract fun weaponDao(): WeaponDao
    abstract fun statDao(): StatDao
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