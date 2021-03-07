package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.DAO.PlanningDAO
import com.example.myapplication.Entity.PlanningEntity

@Database(entities = [PlanningEntity::class], version = 1, exportSchema = false)
abstract class PlanningDataBase : RoomDatabase() {
    abstract val PlanningDAO: PlanningDAO

    companion object {
        @Volatile
        private var INSTANCE: PlanningDataBase? = null

        fun getDatabase(context: Context): PlanningDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlanningDataBase::class.java,
                    "planning_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}