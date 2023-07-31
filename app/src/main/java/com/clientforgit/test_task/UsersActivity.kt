package com.clientforgit.test_task

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clientforgit.test_task.databinding.ActivityUsersBinding
import com.clientforgit.test_task.user.User
import com.clientforgit.test_task.user.UserViewModel


class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var fragment: UserFragment
    private lateinit var userViewModel: UserViewModel
    private lateinit var recycleViewAdapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        initRecyclerView()
        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycleViewAdapter = RecycleViewAdapter(::invokeFragment)
        binding.recyclerView.adapter = recycleViewAdapter
        userViewModel.allUsers.observe(this) {
            recycleViewAdapter.setUsers(it)
        }
    }

    private fun invokeFragment(user: User) {
        userViewModel.selected = user
        fragment = UserFragment()
        fragment.show(supportFragmentManager, "UsersFragment")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        for (position in recycleViewAdapter.changedUsersPosition) {
            userViewModel.updateUser(recycleViewAdapter.getUsers()[position])
        }
    }
}