package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Entity.PlanningEntity

class PlanningListAdapter : ListAdapter<PlanningEntity, PlanningViewHolder>(PlanningComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanningViewHolder {
        return PlanningViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PlanningViewHolder, position: Int) {
        val current: PlanningEntity = getItem(position)
        holder.bind(current.horaire, current.activity)
    }

}

class PlanningViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val HoraireTextView: TextView = itemView.findViewById(R.id.textView1)
    private val ActiviteTextView: TextView = itemView.findViewById(R.id.textView2)


    fun bind(horaire: String?, activité: String?) {
        HoraireTextView.text = horaire
        ActiviteTextView.text = activité
    }

    companion object {
        fun create(parent: ViewGroup): PlanningViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
            return PlanningViewHolder(view)
        }
    }
}

class PlanningComparator : DiffUtil.ItemCallback<PlanningEntity>() {
    override fun areItemsTheSame(oldItem: PlanningEntity, newItem: PlanningEntity): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PlanningEntity, newItem: PlanningEntity): Boolean {
        return ((oldItem.activity == newItem.activity) && (oldItem.horaire == newItem.horaire))
    }
}