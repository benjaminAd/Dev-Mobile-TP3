package com.example.myapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Entity.PlanningEntity
import com.example.myapplication.Repository.PlanningRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlanningAndroidViewModel(application: Application) : AndroidViewModel(application) {
    private val readAllData: MutableLiveData<ArrayList<PlanningEntity>>
    private val repository: PlanningRepository

    init {
        val planningDao = PlanningDataBase.getDatabase(application).PlanningDAO()
        repository = PlanningRepository(planningDao)
        readAllData = repository.readAllData
    }

    fun addPlanning(planningEntity: PlanningEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlanning(planningEntity)
        }
    }
}