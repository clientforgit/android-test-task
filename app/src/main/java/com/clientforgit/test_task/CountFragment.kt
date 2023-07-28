package com.clientforgit.test_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clientforgit.test_task.databinding.FragmentCountBinding

class CountFragment : Fragment(R.layout.fragment_count) {

    private lateinit var binding: FragmentCountBinding
    var countAmount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountBinding.inflate(inflater, container, false)
        binding.counterAmount.text = getString(R.string.counter_amount, countAmount)

        binding.increaseButton.setOnClickListener {
            countAmount++
            binding.counterAmount.text = getString(R.string.counter_amount, countAmount)
        }

        return binding.root
    }
}