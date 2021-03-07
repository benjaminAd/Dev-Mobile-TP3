package com.example.myapplication.Repository

import androidx.lifecycle.LiveData
import com.example.myapplication.DAO.PlanningDAO
import com.example.myapplication.Entity.PlanningEntity

class PlanningRepository(private val planningDAO: PlanningDAO) {
    val readAllData: LiveData<ArrayList<PlanningEntity>> = planningDAO.readAllData()

    suspend fun addPlanning(planningEntity: PlanningEntity) {
        planningDAO.addPlanning(planningEntity)
    }
}