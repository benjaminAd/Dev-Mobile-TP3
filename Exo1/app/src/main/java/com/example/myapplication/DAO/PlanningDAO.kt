package com.example.myapplication.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Entity.PlanningEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanningDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlanning(planning: PlanningEntity)

    @Query("SELECT * FROM planning_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<PlanningEntity>>

    @Query("DELETE FROM planning_table")
    suspend fun deleteAll()
}