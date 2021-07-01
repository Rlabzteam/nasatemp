package `in`.mallaya.nasa.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface PlanetDao {
    @Insert
    fun addPlanet(planet: Planet)

    @Query("SELECT * FROM planet")
    fun getAllPlanet():List<Planet>

    @Insert
    fun addmultiplePlanet(vararg planet: Planet)

}