package com.svruso.serviciosweb

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.svruso.serviciosweb.databinding.ActivityUsuariosBinding
import org.json.JSONArray

class Usuarios : AppCompatActivity() {

    private lateinit var binding: ActivityUsuariosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsuariosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)

        // URL to fetch data from
        val url = "http://chinguenasumadre.servidoreselruso.com:8080/api/users"
        val lista = mutableListOf<String>()

        // Listener for successful response
        val listener = Response.Listener<JSONArray> { result ->
            for (i in 0 until result.length()) {
                val user = result.getJSONObject(i)
                lista.add(
                    user.getString("name") + "\n" +
                            user.optString("apellido", "No apellido") + "\n" + // Optional field handling
                            user.getString("email") + "\n" +
                            user.optInt("edad", 0) // Use optInt for optional age field
                )
            }
            // Set the adapter with the updated list
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
            binding.lvLista.adapter = adapter
        }

        // Listener for errors
        val errorListener = Response.ErrorListener { error ->
            Log.e("Volley", error.message ?: "Error")
        }

        // Create the request
        val request = JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener)

        // Add the request to the RequestQueue
        queue.add(request)


    }

    //Pegue el menu al activity

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_usuarios, menu)
        return true
    }

    //Configure las acciones de las opciones del menu

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mnuRegistrar -> {
                val intent = Intent(this@Usuarios, Registrar::class.java)
                startActivity(intent)
            }
            R.id.mnuSalir -> finish()
        }
        return true
    }
}
