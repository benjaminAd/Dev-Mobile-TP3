package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.DAO.PlanningDAO
import com.example.myapplication.Entity.PlanningEntity
import kotlinx.coroutines.flow.Flow

class PlanningRepository(private val planningDAO: PlanningDAO) {
    val readAllData: LiveData<List<PlanningEntity>> = planningDAO.readAllData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addPlanning(planningEntity: PlanningEntity) {
        planningDAO.addPlanning(planningEntity)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        planningDAO.deleteAll()
    }


}