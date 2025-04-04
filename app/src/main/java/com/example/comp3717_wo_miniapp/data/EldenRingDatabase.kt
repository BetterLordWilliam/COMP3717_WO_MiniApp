package com.example.comp3717_wo_miniapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comp3717_wo_miniapp.data.dataobjects.WeaponDao
import com.example.comp3717_wo_miniapp.data.models.WeaponEntity

@Database(entities = [
    WeaponEntity::class,
    NumericStatValue::class,
    StringStatValue::class
 ], version = 3)
abstract class EldenRingDatabase : RoomDatabase() {
    abstract fun weaponDao(): WeaponDao
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