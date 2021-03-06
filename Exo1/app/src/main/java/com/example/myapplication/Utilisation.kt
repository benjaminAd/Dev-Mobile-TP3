package com.example.myapplication

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class Utilisation : LifecycleObserver {
    companion object {
        var NbUse: Int = 0
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun nombreUtilisation() {
        NbUse += 1
        println("----------------------Observer ON RESUME $NbUse")
    }


}
