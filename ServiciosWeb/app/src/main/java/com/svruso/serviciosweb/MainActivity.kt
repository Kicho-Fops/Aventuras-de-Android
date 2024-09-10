

package com.svruso.serviciosweb

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.svruso.serviciosweb.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Volley
    """
    Volley es una libreria de google para consultas http
    
    Se instala en app, add dependencies
    """

    // Delcaracion de la cola

        var queue = Volley.newRequestQueue(this)

        // Tipos de request con volley
        // StringRequest
        // JsonObjectRequest
        // JsonArrayRequest
        // ImageRequest

        // La peticion puede recibir como parametros
        // Metodo, URL, Body, Response.Listener, Response.ErrorListener


        val url = "https://jsonplaceholder.typicode.com/users"
        val lista = mutableListOf<String>()

        val listener = Response.Listener<JSONArray> { result ->
            /*for(i in 0 until result.length())
                lista.add(result.getJSONObject(i).getString("name")
                        + "\n" + result.getJSONObject(i).getString("email")
                        + "\n" + result.getJSONObject(i).getJSONObject("address").getString("street")
                )*/


            result.map { jsonObject ->
                lista.add(jsonObject.getString("name")
                        + "\n" + jsonObject.getString("email")
                        + "\n" + jsonObject.getJSONObject("address").getString("street")
                )
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, lista)
            binding.lvLista.adapter = adapter
        }

        val errorListener = Response.ErrorListener { error ->
            Log.e("Volley", error.message ?: "Error")
        }
        // Metodo, URL, Body, Response.Listener, Response.ErrorListener
        val request = JsonArrayRequest(Request.Method.GET, url, null, listener, errorListener)
        queue.add(request)


        /*
        * Retrofit
        *
        * Retrofit puede parsear las respuestas de las peticiones directamente a POJOs (modelos)
        *
        * Para configurar retrofit se necesita:
        * Una interfaz con los metodos que se van a usar
        * Un modelo al que se conviertan los datos de la respuesta
        * la llamada al servicio que implementara los metodos callback para el tratamiento de la informacion recibida
        * Tambien se necesitan anotaciones para configurar la peticion
        *
        *
        * */


//        // Interfaz -> esto es en IServicio.kt
//        interface IServicio {
//            @GET(value = "users")
//            fun getUsers(): Call<List<User>>
//
//            @GET(value = "users/{id}")
//            fun getUser(@Path("id") id: Int): Call<User>
//
//            @POST(value = "users")
//            fun updateUserById(@Path(value = "id") id: Int,
//                               @Body user: User?): Call<User>
//        }



    }

}

fun JSONArray.map(next: (JSONObject) -> Unit) {
    for(i in 0 until this.length()) {
        next(this.getJSONObject(i))
    }
}