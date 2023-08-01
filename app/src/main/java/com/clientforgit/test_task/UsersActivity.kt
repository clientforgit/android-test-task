package com.clientforgit.test_task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.clientforgit.test_task.databinding.ActivityUsersBinding
import com.clientforgit.test_task.shibe.ShibeFetcher


class UsersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding
    private lateinit var recycleViewAdapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersBinding.inflate(layoutInflater)
        initRecyclerView()
        setContentView(binding.root)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL)
        recycleViewAdapter = RecycleViewAdapter(applicationContext)
        binding.recyclerView.adapter = recycleViewAdapter
        binding.recyclerView.itemAnimator = null
        val shibeFetcher = ShibeFetcher(applicationContext)
        shibeFetcher.fetchList { list ->
            recycleViewAdapter.addShibes(list)
        }
    }
}