package com.svruso.intents

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.svruso.intents.databinding.ActivityMonitorBinding

class Monitor : AppCompatActivity() {

    private lateinit var binding: ActivityMonitorBinding

    var option: String = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMonitorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memoria: String = intent.getStringExtra("memoria").orEmpty()
        val cpu: String = intent.getStringExtra("CPU").orEmpty()
        val gpu: String = intent.getStringExtra("GPU").orEmpty()

        binding.tvCantidadGpu.text = gpu

        binding.rbMonitorLow.setOnClickListener {
            option = binding.rbMonitorLow.text.toString()
        }

        binding.rbMonitorMedium.setOnClickListener {
            option = binding.rbMonitorMedium.text.toString()
        }

        binding.rbMonitorHigh.setOnClickListener {
            option = binding.rbMonitorHigh.text.toString()
        }


        binding.btnSiguiente4.setOnClickListener {

            val intent = Intent(this@Monitor, Results::class.java)

            intent.putExtra("monitor", option)
            intent.putExtra("memoria", memoria)
            intent.putExtra("CPU", cpu)
            intent.putExtra("GPU", gpu)

            startActivity(intent)

        }

    }
}