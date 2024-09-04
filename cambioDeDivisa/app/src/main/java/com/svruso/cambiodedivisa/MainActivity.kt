package com.svruso.cambiodedivisa

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.svruso.cambiodedivisa.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val currencies = arrayOf("USD", "EUR", "GBP", "JPY", "CNY")
        var result: Float = 0f // Var para cuando si se puede cambiar el valor

        binding.spnConvertFrom.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencies)
        binding.spnConvertTo.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, currencies)

        binding.btnConvert.setOnClickListener() {
            val ConvertFrom = binding.spnConvertFrom.selectedItem.toString()
            val ConvertTo = binding.spnConvertTo.selectedItem.toString()
            var amount = binding.edtFrom.text.toString()

            if (amount.isEmpty() == true) {
                Log.e("ERROR", "El valor ingresado no es un número")
                Toast.makeText(this, "Ingrese un valor valido porfavor", Toast.LENGTH_LONG).show()

            }
            else {
                result = convertCurrencies(ConvertFrom, ConvertTo, amount.toFloat())
            }


            binding.txtFinal.text = result.toString()
        }





    }

    private fun convertCurrencies(from: String, to: String, amount: Float): Float {
        val conversionMatrix = arrayOf(
            // USD, EUR, GBP, JPY, CNY
            floatArrayOf(1.0f, 0.85f, 0.73f, 110.0f, 6.4f), // USD
            floatArrayOf(1.18f, 1.0f, 0.86f, 129.0f, 7.5f), // EUR
            floatArrayOf(1.37f, 1.16f, 1.0f, 150.0f, 8.7f), // GBP
            floatArrayOf(0.0091f, 0.0078f, 0.0067f, 1.0f, 0.058f),  // JPY
            floatArrayOf(0.16f, 0.14f, 0.12f, 17.0f, 1.0f) // CNY
        )

        val fromIndex = getIndex(from)
        val toIndex = getIndex(to)

        return if (fromIndex != -1 && toIndex != -1) {
            amount * conversionMatrix[fromIndex][toIndex]
        } else {
            0f
        }
    }

    private fun getIndex(currency: String): Int {
        return when (currency) {
            "USD" -> 0
            "EUR" -> 1
            "GBP" -> 2
            "JPY" -> 3
            "CNY" -> 4
            else -> -1
        }
    }
}



