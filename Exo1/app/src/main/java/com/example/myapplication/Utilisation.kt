package com.example.myapplication

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.io.FileInputStream
import java.io.FileOutputStream

class Utilisation(private var NbUse: Int) : LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun nombreUtilisation() {
        NbUse += 1
        println("----------------------Observer ON RESUME $NbUse")
    }

    fun getNbUse(): Int {
        return this.NbUse
    }

    fun setNbUse(NbUse: Int) {
        this.NbUse = NbUse
    }

}
