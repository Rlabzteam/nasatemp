package `in`.mallaya.nasa

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

interface APIServicecs {

    @GET("/planetary/apod/?")
    fun getPlanetaryData(@Query("api_key") api_key: String, @Query("count") count: String): Call<JsonArray>
}