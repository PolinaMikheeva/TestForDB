package com.example.testpractice

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testpractice.databinding.FragmentDeadlineBinding
import com.example.testpractice.databinding.ItemExerciseBinding

class DeadlineHolder(
    private val binding: ItemExerciseBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(task: Task){
        with(binding){
            checkBox.text = task.name
        }
    }
}
