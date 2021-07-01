package `in`.mallaya.nasa.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Planet::class],version = 1)
abstract class PlanetDatabase : RoomDatabase(){

    abstract fun getPlanetDao():PlanetDao

    companion object{
        @Volatile
        private var instance:PlanetDatabase?=null
        private val LOCk=Any()
        operator fun invoke(context: Context)= instance?: synchronized(LOCk){
            instance?: buildDatabase(context).also {
                instance=it
            }
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(context.applicationContext,
            PlanetDatabase::class.java,
            "planetdatabase"
        ).build()
    }
}