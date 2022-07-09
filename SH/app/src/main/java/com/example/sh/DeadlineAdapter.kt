package com.example.sh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sh.databinding.ItemExerciseBinding

class DeadlineAdapter(
    private val list: List<Task>
) : RecyclerView.Adapter<DeadlineHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeadlineHolder = DeadlineHolder(
        ItemExerciseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: DeadlineHolder,
        position: Int
    ) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size
}