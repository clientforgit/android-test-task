package com.clientforgit.test_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.clientforgit.test_task.databinding.FragmentUsersBinding
import com.clientforgit.test_task.user.UserViewModel

class UserFragment : DialogFragment(R.layout.fragment_users) {

    private lateinit var binding: FragmentUsersBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        userViewModel = ViewModelProvider(requireActivity())[UserViewModel::class.java]

        binding.fragmentUserName.text = getString(R.string.fragment_name_tv, userViewModel.selected.name)
        binding.fragmentUserAge.text = getString(R.string.fragment_age_tv, userViewModel.selected.age)
        binding.fragmentUserIsStudent.text = getString(R.string.fragment_is_student_tv, userViewModel.selected.isStudent)

        return binding.root
    }
}