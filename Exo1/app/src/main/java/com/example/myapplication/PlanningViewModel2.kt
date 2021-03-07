package com.example.myapplication

import android.app.Application
import androidx.lifecycle.*
import com.example.myapplication.Entity.PlanningEntity
import com.example.myapplication.Repository.PlanningRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class PlanningViewModel2(private val repository: PlanningRepository) : ViewModel() {
    val readAllData: LiveData<List<PlanningEntity>> = repository.readAllData


    fun addPlanning(planningEntity: PlanningEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlanning(planningEntity)
        }
    }

}

class PlanningViewModelFactory(private val repository: PlanningRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanningViewModel2::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlanningViewModel2(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}