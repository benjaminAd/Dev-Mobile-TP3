package com.example.myapplication

class Creneau(private var horaire: String, private var Activite: String) {
    fun getHoraire(): String {
        return this.horaire
    }

    fun setHoraire(horaire: String) {
        this.horaire = horaire
    }

    fun getActivite(): String {
        return this.Activite
    }

    fun setActivite(Activite: String) {
        this.Activite = Activite
    }

    override fun toString(): String {
        return "$horaire : $Activite"
    }


}