package com.example.comp3717_wo_miniapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.comp3717_wo_miniapp.data.dataobjects.ArmourDao
import com.example.comp3717_wo_miniapp.data.dataobjects.IncantationDao
import com.example.comp3717_wo_miniapp.data.dataobjects.ItemDao
import com.example.comp3717_wo_miniapp.data.dataobjects.ShieldDao
import com.example.comp3717_wo_miniapp.data.dataobjects.SorceryDao
import com.example.comp3717_wo_miniapp.data.dataobjects.StatDao
import com.example.comp3717_wo_miniapp.data.dataobjects.TalismanDao
import com.example.comp3717_wo_miniapp.data.dataobjects.WeaponDao
import com.example.comp3717_wo_miniapp.data.entites.ArmourEntity
import com.example.comp3717_wo_miniapp.data.entites.AttackStatEntity
import com.example.comp3717_wo_miniapp.data.entites.DamageNegationStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.RequiredAttributeStatEntity
import com.example.comp3717_wo_miniapp.data.entites.ResistenceStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.ScalingStatsEntity
import com.example.comp3717_wo_miniapp.data.entites.WeaponEntity
import com.example.comp3717_wo_miniapp.data.entites.DefenceStatEntity
import com.example.comp3717_wo_miniapp.data.entites.IncantationEntity
import com.example.comp3717_wo_miniapp.data.entites.ItemEntity
import com.example.comp3717_wo_miniapp.data.entites.ShieldEntity
import com.example.comp3717_wo_miniapp.data.entites.SorceryEntity
import com.example.comp3717_wo_miniapp.data.entites.TalismanEntity

@Database(entities = [
    AttackStatEntity::class,
    DefenceStatEntity::class,
    RequiredAttributeStatEntity::class,
    ScalingStatsEntity::class,
    DamageNegationStatsEntity::class,
    ResistenceStatsEntity::class,
    WeaponEntity::class,
    ShieldEntity::class,
    IncantationEntity::class,
    SorceryEntity::class,
    TalismanEntity::class,
    ArmourEntity::class,
    ItemEntity::class,
 ], version = 16)
abstract class EldenRingDatabase : RoomDatabase() {
    abstract fun weaponDao(): WeaponDao
    abstract fun statDao(): StatDao
    abstract fun armourDao(): ArmourDao
    abstract fun shieldDao(): ShieldDao
    abstract fun incantationDao(): IncantationDao
    abstract fun sorceryDao(): SorceryDao
    abstract fun talismanDao(): TalismanDao
    abstract fun itemDao(): ItemDao
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