package com.example.testpractice

import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.example.testpractice.databinding.FragmentDeadlineBinding

class DeadlineFragment : Fragment(R.layout.fragment_deadline) {

    private var _binding : FragmentDeadlineBinding? = null
    private val binding get() = _binding!!

    private var adapter: DeadlineAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDeadlineBinding.bind(view)

        adapter = DeadlineAdapter(TaskRepository.tasks)
        binding.rvDeadline.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}