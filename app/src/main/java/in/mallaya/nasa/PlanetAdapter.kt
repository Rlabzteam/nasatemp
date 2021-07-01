package `in`.mallaya.nasa

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class PlanetAdapter(val context: Context, val planet:ArrayList<Planetary>):RecyclerView.Adapter<PlanetAdapter.PlanetViewHolder>() {
    class PlanetViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        var planetImage=itemView.findViewById<ImageView>(R.id.img)
        var Title=itemView.findViewById<TextView>(R.id.title)
        var date=itemView.findViewById<TextView>(R.id.date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.datalist,parent,false)
    return PlanetViewHolder(view)
    }


    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planet:Planetary=planet[position]
        holder.Title.text=planet.title


        val local:LocalDate= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.parse(planet.date)
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        val datef:DateTimeFormatter= DateTimeFormatter.ofPattern("MMM dd, yyyy")
        var d:String=datef.format(local)

        holder.date.text=d

        Glide.with(context)
            .load(planet.url)
            .circleCrop()
            .into(holder.planetImage)

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("title",planet.title)
            intent.putExtra("image",planet.url)
            intent.putExtra("dec",planet.explanation)
            intent.putExtra("date",planet.date)
            context.startActivity(intent)
        })

    }

    override fun getItemCount(): Int {
        return planet.size
    }
}