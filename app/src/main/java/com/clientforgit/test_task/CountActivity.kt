package com.clientforgit.test_task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.clientforgit.test_task.databinding.ActivityCountBinding

class CountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCountBinding
    private val amount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountBinding.inflate(layoutInflater)

        // if CountActivity class was invoked by ContainerActivity
        if (intent.hasExtra("amount")) {
            binding.activityViewText.text = getString(R.string.result_count, intent.getIntExtra("amount", 0))
        } else {
            binding.activityViewText.text = getString(R.string.result_count, amount)
        }

        binding.openFragmentButton.setOnClickListener {
            intent = Intent(this@CountActivity, ContainerActivity::class.java)
            startActivity(intent)
        }

        setContentView(binding.root)
    }
}