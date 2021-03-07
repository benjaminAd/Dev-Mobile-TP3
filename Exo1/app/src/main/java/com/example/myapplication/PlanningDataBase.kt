package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.DAO.PlanningDAO
import com.example.myapplication.Entity.PlanningEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [PlanningEntity::class], version = 1, exportSchema = false)
abstract class PlanningDataBase : RoomDatabase() {
    abstract fun PlanningDAO(): PlanningDAO

    private class PlanningDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var planningDao = database.PlanningDAO()

                    // Delete all content here.
                    planningDao.deleteAll()

                    val planning1 =
                        PlanningEntity(1, "08h-10h", "Rencontre avec Client DUPONT")
                    val planning2 = PlanningEntity(
                        2,
                        "10h-12h",
                        "Travailler le dossier recrutement"
                    )
                    val planning3 = PlanningEntity(3, "14h-16h", "Réunion équipe")
                    val planning4 =
                        PlanningEntity(4, "16h-18h", "Préparation dossier vente")
                    planningDao.addPlanning(planning1)
                    planningDao.addPlanning(planning2)
                    planningDao.addPlanning(planning3)
                    planningDao.addPlanning(planning4)

                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PlanningDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): PlanningDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlanningDataBase::class.java,
                    "planning_database"
                ).addCallback(PlanningDatabaseCallback(scope)).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}