package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Entity.PlanningEntity
import com.example.myapplication.Repository.PlanningRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class PlanningAndroidViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<ArrayList<PlanningEntity>>
    private val repository: PlanningRepository

    init {
        val planningDao = PlanningDataBase.getDatabase(application).PlanningDAO
        repository = PlanningRepository(planningDao)
        readAllData = repository.readAllData
    }

    fun addPlanning(planningEntity: PlanningEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlanning(planningEntity)
        }
    }

    fun addBasicsData() {
        addPlanning(PlanningEntity(UUID.randomUUID(), "8h-10h", "Developpement mobile"))
        addPlanning(
            PlanningEntity(
                UUID.randomUUID(),
                "10h-12h",
                "Représentation des connaissances"
            )
        )
        addPlanning(PlanningEntity(UUID.randomUUID(), "14h-16h", "Web Sémantique"))
        addPlanning(PlanningEntity(UUID.randomUUID(), "16h-18h", "TER"))
    }

}