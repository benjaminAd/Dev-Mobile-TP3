package com.example.myapplication.DAO

import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Entity.PlanningEntity

@Dao
interface PlanningDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlanning(planning: PlanningEntity)

    @Query("SELECT * FROM planning_table ORDER BY id ASC")
    fun readAllData(): MutableLiveData<ArrayList<PlanningEntity>>
}