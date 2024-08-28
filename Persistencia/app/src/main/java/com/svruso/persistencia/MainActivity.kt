package com.svruso.persistencia

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.svruso.persistencia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sp = getSharedPreferences("sesion", MODE_PRIVATE)

        val user = sp.getString("user", "xd")
        if (user != "xd"){
            // Cambiar de actividad, clereando la queue y añadiendo Home en este caso
            val intent = Intent(this@MainActivity,
                Home::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(intent)
        }












        // Verificar si el usuario y contraseña son correctos

        binding.btnNext.setOnClickListener { if (binding.etName.text.toString() == "admin" &&
            binding.etPassword.text.toString() == "123"){

            // Escribir "algo" en el sharedPreferences



//            sp.edit().putString("user", binding.etName.text.toString())
            with(sp.edit()){
                putString("user", binding.etName.text.toString())
                putString("password", binding.etPassword.text.toString())
                commit()
            }

            // Cambiar de actividad, clereando la queue y añadiendo Home en este caso
            val intent = Intent(this@MainActivity,
                Home::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_NEW_TASK)

            startActivity(intent)

        } }










        // Cambiar de acrivity


    }
}