package com.example.myapplication

import androidx.lifecycle.ViewModel

class PlanningViewModel : ViewModel() {
    private val planning: ArrayList<Creneau> = ArrayList<Creneau>()

    fun getPlanning(): ArrayList<Creneau> {
        return this.planning
    }

    fun addCreneau(creneau: Creneau) {
        this.planning.add(creneau)
    }

    fun removeCreneau(creneau: Creneau) {
        this.planning.remove(creneau)
    }

}