package com.example.myapplication

import android.app.Application
import com.example.myapplication.Repository.PlanningRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class PlanningApplication : Application() {
    val dataBase by lazy { PlanningDataBase.getDatabase(this, applicationScope) }
    val repository by lazy { PlanningRepository(dataBase.PlanningDAO()) }
    val applicationScope = CoroutineScope(SupervisorJob())

}