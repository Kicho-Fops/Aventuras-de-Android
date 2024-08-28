package com.svruso.intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.svruso.intents.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.txtUsuario.text = intent.getStringExtra("usuario")
        val memoria: String = intent.getStringExtra("memoria").orEmpty()
        binding.tvCantidadRam.text = intent.getStringExtra("memoria").orEmpty()

        binding.btnSiguiente2.setOnClickListener {

            val intent = Intent(this@Home, Videocard::class.java)
            intent.putExtra("memoria", memoria) // Pasamos la variable desde la activity pasada

            // Recaudar opcion de button group

            val opcion = when(binding.rgCpu.checkedRadioButtonId){

                R.id.rb2Core -> "2 Núcleos" // Todo esto se debe de convertir en un recurso de @string, pero me da flojera hacer eso
                R.id.rb4Core -> "4 Núcleos"
                R.id.rb8Core -> "8 Núcleos"
                R.id.rb16Core -> "16 Núcleos"
                else -> ""
            }
            intent.putExtra("CPU", opcion) // Pasamos nueva opcion
            startActivity(intent)

        }


        }
    }
