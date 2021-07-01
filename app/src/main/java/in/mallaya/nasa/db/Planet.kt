package `in`.mallaya.nasa.db

import android.icu.text.CaseMap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Planet(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    val title: String,
    val date:String,
    val explanation:String,
    val url:String
    )



