package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlanningViewModel : ViewModel() {
    private var slots: MutableLiveData<ArrayList<Creneau>> = MutableLiveData()

    private fun initCreneau() {
        var list = ArrayList<Creneau>()
        list.add(Creneau("08h-10h", "Rencontre avec Client DUPONT"))
        list.add(Creneau("10h-12h", "Travailler le dossier recrutement"))
        list.add(Creneau("14h-16h", "Réunion équipe"))
        list.add(Creneau("16h-18h", "Préparation dossier vente"))
        this.slots.value = list
    }

    fun getPlanning(): MutableLiveData<ArrayList<Creneau>> {
        initCreneau()
        return this.slots
    }


}