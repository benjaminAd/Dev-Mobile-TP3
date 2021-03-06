package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.ViewModelProvider

class Planning : AppCompatActivity() {

    private lateinit var listeView: ListView

    private lateinit var planningViewModel: PlanningViewModel
    private lateinit var planning: ArrayList<Creneau>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_planning)
        listeView = findViewById<ListView>(R.id.ListViewPlanning)
        planningViewModel = ViewModelProvider(this)[PlanningViewModel::class.java]
        planning = planningViewModel.getPlanning()
        val arrayPlanning = arrayOfNulls<Creneau>(planning.size)
        planning.toArray(arrayPlanning)
        val arrayAdapter =
            ArrayAdapter<Creneau>(this, android.R.layout.simple_list_item_1, arrayPlanning)
        listeView.adapter = arrayAdapter
    }
}