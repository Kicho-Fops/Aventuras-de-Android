package com.svruso.intents

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.svruso.intents.databinding.ActivityResultsBinding

class Results : AppCompatActivity() {

    private lateinit var binding: ActivityResultsBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memoria: String = intent.getStringExtra("memoria").orEmpty()
        val cpu: String = intent.getStringExtra("CPU").orEmpty()
        val gpu: String = intent.getStringExtra("GPU").orEmpty()
        val monitor: String = intent.getStringExtra("monitor").orEmpty()


        binding.run {
            tvCPUResult.text = cpu
            tvGPUResult.text = gpu
            tvMonitorResult.text = monitor
            tvRAMResult.text = memoria
            btnReturn.setOnClickListener {

                val intent = Intent(this@Results, MainActivity::class.java)


                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)


            }
        }



    }
}