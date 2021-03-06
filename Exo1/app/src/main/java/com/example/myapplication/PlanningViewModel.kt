package com.example.myapplication

import androidx.lifecycle.ViewModel

class PlanningViewModel : ViewModel() {
    private val planning: ArrayList<Creneau> = ArrayList<Creneau>()

    private fun initCreneau() {
        addCreneau(Creneau("08h-10h", "Rencontre avec Client DUPONT"))
        addCreneau(Creneau("10h-12h", "Travailler le dossier recrutement"))
        addCreneau(Creneau("14h-16h", "Réunion équipe"))
        addCreneau(Creneau("16h-18h", "Préparation dossier vente"))
    }

    fun getPlanning(): ArrayList<Creneau> {
        initCreneau()
        return this.planning
    }

    fun addCreneau(creneau: Creneau) {
        this.planning.add(creneau)
    }

    fun removeCreneau(creneau: Creneau) {
        this.planning.remove(creneau)
    }

}