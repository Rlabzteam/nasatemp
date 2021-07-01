package `in`.mallaya.nasa

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.bumptech.glide.Glide

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var actionBar:ActionBar?=supportActionBar
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
        }

        actionBar?.setTitle("APOD Details")

        val  imageview:ImageView=findViewById(R.id.task_image)
        val textTitle: TextView =findViewById(R.id.title)
        val textdate:TextView=findViewById(R.id.date)
        val textdec:TextView=findViewById(R.id.textdec)

        val title:String=intent.getStringExtra("title").toString()
        val image:String=intent.getStringExtra("image").toString()
        val dec:String=intent.getStringExtra("dec").toString()
        val date:String=intent.getStringExtra("date").toString()
        Glide.with(this)
            .load(image)
            .into(imageview)

            textTitle.setText(title)
        textdec.setText(dec)
        textdate.setText(date)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }



}