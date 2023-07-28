package com.clientforgit.test_task

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.clientforgit.test_task.databinding.ActivityContainerBinding


class ContainerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContainerBinding
    private val countFragment = CountFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContainerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.commit{
            replace(R.id.fragment_container, countFragment)
        }

        // displaying back arrow on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this@ContainerActivity, CountActivity::class.java)

        // if flags FLAG_ACTIVITY_CLEAR_TOP and FLAG_ACTIVITY_NEW_TASK added, invoked activity
        // will be brought to the foreground and given focus
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("amount", countFragment.countAmount)
        startActivity(intent)
        finish()
        return true
    }
}