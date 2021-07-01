package `in`.mallaya.nasa


import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.view.Gravity
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM




class Dashboard : AppCompatActivity() {

    var BaseUrl: String = "https://api.nasa.gov"

    var recyclerView:RecyclerView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        var actionBar: ActionBar?=supportActionBar

        actionBar?.setTitle("APOD List")
        recyclerView=findViewById(R.id.recyclerView)




        getdata()

       // PlanetDatabase(this).getPlanetDao()
    }



    private fun getdata() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIServicecs::class.java)

        val call: Call<JsonArray> = service.getPlanetaryData("DEMO_KEY", "10")

        call.enqueue(object : Callback<JsonArray> {
            override fun onResponse(
                call: Call<JsonArray>,
                response: Response<JsonArray>
            ) {

                var jsonArray: JsonArray ?= response!!.body()
               // if (response?.body() != null)

               var obj=jsonArray?.asJsonArray

                var serveresponse: ArrayList<Planetary> = getGeneral(obj.toString())

                Log.d("output", serveresponse.toString())
                mydata(serveresponse)

            }
            private fun getGeneral(s: String): ArrayList<Planetary> {
                val gson = Gson()
                val listType = object : TypeToken<ArrayList<Planetary>>() {}.type
                return gson.fromJson<ArrayList<Planetary>>(s, listType)
            }
            override fun onFailure(call: Call<JsonArray>, t: Throwable?) {
                Toast.makeText(applicationContext, "hi this just failed", Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun mydata(taskresponse: ArrayList<Planetary> ) {
        recyclerView!!.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        val taskinclassAdapter = PlanetAdapter(this, taskresponse)
        recyclerView!!.adapter = taskinclassAdapter
    }
}