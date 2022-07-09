package com.example.sh

import androidx.recyclerview.widget.RecyclerView
import com.example.sh.databinding.ItemExerciseBinding

class DeadlineHolder(
    private val binding: ItemExerciseBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(task: Task){
        with(binding){
            checkBox.text = task.name
        }
    }
}
