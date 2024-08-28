package com.svruso.intents

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.svruso.intents.databinding.ActivityVideocardBinding

class Videocard : AppCompatActivity() {

    private lateinit var binding: ActivityVideocardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVideocardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val memoria: String = intent.getStringExtra("memoria").orEmpty()
        val cpu: String = intent.getStringExtra("CPU").orEmpty()

        binding.tvCantidadCpu.text = cpu

        binding.btnSiguiente3.setOnClickListener {

            val intent = Intent(this@Videocard, Monitor::class.java)

            // Seguimos arrastrando variables
            intent.putExtra("memoria", memoria)
            intent.putExtra("CPU", cpu)

            val opcion = when(binding.rbGpu.checkedRadioButtonId){

                R.id.rbGpuWeak -> "Débil"
                R.id.rbGpuNormal -> "Promedio"
                R.id.rbGpuTop -> "Potente"
                else -> ""
            }

            intent.putExtra("GPU", opcion)
            startActivity(intent)

        }


    }
}