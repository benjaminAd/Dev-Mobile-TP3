package com.example.myapplication.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "planning_table")
data class PlanningEntity(
    @PrimaryKey(autoGenerate = true)
    val id: UUID,
    val horaire: String,
    val activity: String
)
