package com.svruso.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnStart = findViewById<Button>(R.id.btnStart)
        val btnPassword = findViewById<Button>(R.id.btnPassword)
        val edtUser = findViewById<EditText>(R.id.edtUser)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)

        val txtMessage = findViewById<TextView>(R.id.txtMessage)


        btnStart.setOnClickListener {

            Toast.makeText(this@MainActivity,
                edtUser.text, Toast.LENGTH_LONG).show()

            // Los controles de tipo texto se manipulan de formas distintas
            txtMessage.text = "Exactamente, ¡Que chichotas!"
            edtUser.setText(getString(R.string.orales))




        }


    }
}