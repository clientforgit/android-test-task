package com.clientforgit.test_task

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.clientforgit.test_task.databinding.FragmentAddUserBinding
import com.clientforgit.test_task.user.User
import com.clientforgit.test_task.user.UserViewModel
import java.util.Calendar


class AddUserFragment(private var notifyDataSetChanged: () -> Unit) : DialogFragment(R.layout.fragment_add_user) {

    private lateinit var binding: FragmentAddUserBinding
    private lateinit var userViewModel: UserViewModel
    private lateinit var datePickerDialog: DatePickerDialog
    private var isDatePickerPicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddUserBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]
        initDatePicker()

        binding.datePicker.setOnClickListener {
            openDatePicker()
        }

        binding.createUserButton.setOnClickListener {
            if (binding.nameInput.text.isNotEmpty() and binding.ageInput.text.isNotEmpty() and isDatePickerPicked) {
                userViewModel.addUser(User(
                    binding.nameInput.text.toString(),
                    binding.ageInput.text.toString().toInt(),
                    binding.datePicker.text.toString()
                ))
                notifyDataSetChanged()
                dismiss()
                Toast.makeText(context, "User added successfully", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    private fun initDatePicker() {
        val dateSetListener = OnDateSetListener { datePicker, year, monthIndex, day ->
            isDatePickerPicked = true
            var month = monthIndex
            month += 1
            val date = String.format("%02d", day) + "/" + String.format("%02d", month) + "/$year"
            binding.datePicker.text = date
        }

        val calendar = Calendar.getInstance()

        datePickerDialog = DatePickerDialog(
            requireContext(),
            com.google.android.material.R.style.Theme_Material3_Dark_Dialog_Alert,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    private fun openDatePicker() {
        datePickerDialog.show()
    }
}