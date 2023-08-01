package com.clientforgit.test_task

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
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
    private lateinit var userFragment: UserFragment
    private lateinit var addUserFragment: AddUserFragment
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
        userFragment = UserFragment()
        userFragment.show(supportFragmentManager, "UsersFragment")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        for (position in recycleViewAdapter.changedUsersPosition) {
            userViewModel.updateUser(recycleViewAdapter.getUsers()[position])
            recycleViewAdapter.changedUsersPosition = mutableSetOf()
        }
    }

    override fun onStop() {
        super.onStop()
        for (position in recycleViewAdapter.changedUsersPosition) {
            userViewModel.updateUser(recycleViewAdapter.getUsers()[position])
        }
        recycleViewAdapter.changedUsersPosition = mutableSetOf()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add_user -> {
                addUserFragment = AddUserFragment { binding.recyclerView.adapter?.notifyDataSetChanged() }
                addUserFragment.show(supportFragmentManager, "AddUsersFragment")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}