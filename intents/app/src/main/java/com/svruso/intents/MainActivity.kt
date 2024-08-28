package com.svruso.intents

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.svruso.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding // <- Atributo de clase, es de tipo ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSiguiente.setOnClickListener {
            val intents = Intent(this@MainActivity, Home::class.java)
            // Enviar datos entre activities
            intents.putExtra("usuario", "Hugo")

            // Controlar el stack de actividades

            // En este caso no queremos hacer esto porque en los requerimientos se pide que se pueda regresar
//            intents.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or
//                            Intent.FLAG_ACTIVITY_NEW_TASK)
            val opcion = when(binding.rgMemoria.checkedRadioButtonId){
                R.id.rb8GB -> "8GB"
                R.id.rb16GB -> "16GB"
                R.id.rb32GB -> "32GB"
                else -> ""
            }
            intents.putExtra("memoria", opcion)
            startActivity(intents)
        }


    }
}