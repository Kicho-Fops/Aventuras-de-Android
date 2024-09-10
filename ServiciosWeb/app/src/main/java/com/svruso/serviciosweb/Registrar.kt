package com.svruso.serviciosweb

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.svruso.serviciosweb.databinding.ActivityRegistrarBinding
import org.json.JSONArray
import org.json.JSONObject

class Registrar : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val queue = Volley.newRequestQueue(this)

        // URL to fetch data from
        val url = "http://chinguenasumadre.servidoreselruso.com:8080/api/users"

        val listener = Response.Listener<JSONObject> { result ->
            try {
                result.getString("name")
                Toast.makeText(
                    this@Registrar,
                    "Registros insertado Correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (ex: Exception) {
                Toast.makeText(this@Registrar, "Algo paso", Toast.LENGTH_SHORT).show()

            }
        }   



        // Listener for errors
        val errorListener = Response.ErrorListener { error ->
            Log.e("Volley", error.message ?: "Error")
        }

        // Create the request
        binding.btnSave.setOnClickListener {
            val body = JSONObject()
            body.put("name", binding.edtName.text.toString())
            body.put("apellido", binding.edtApellido.text.toString())
            body.put("correo", binding.edtCorreo.text.toString())
            body.put("edad", binding.edtNum.text.toString())
            val request = JsonObjectRequest(Request.Method.POST, url, body, listener, errorListener)
            queue.add(request)

        }

        // Add the request to the RequestQueue




    }
}