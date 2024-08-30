package com.svruso.persistencia

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.svruso.persistencia.databinding.ActivityHomeBinding
import com.svruso.persistencia.databinding.ActivityMainBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Adaptadores

        val foodList = listOf("Pizza", "Tacos", "Arroz", "Gorditas", "Flautas", "Hamburguesa", "Chimichangas")

        // Contexto, layout, datos
        val adapter = ArrayAdapter(this@Home,
            android.R.layout.simple_list_item_1,
            foodList)

        binding.spFood.adapter = adapter


        binding.btnClose.setOnClickListener() {

            // Borrar los sharedpreferences

            val sp = getSharedPreferences("sesion", MODE_PRIVATE)

            with(sp.edit()){
                remove("user")
                remove("password")
                commit()
            }


            // Regresamos a la actividad de login

            val intent = Intent(this@Home,
                MainActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(intent)


        }




    }
}