package com.technosp.wallpaper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView

class MainActivity : AppCompatActivity() {

    private lateinit var imageSlider: SliderView
    private lateinit var natureLayout:LinearLayout
    private lateinit var wildlifeLayout: LinearLayout
    private lateinit var neonArtLayout: LinearLayout
    private lateinit var vehiclesLayout: LinearLayout
    private lateinit var homeRv: RecyclerView
    private lateinit var progressBar: ProgressBar

    private val images= intArrayOf(
        R.drawable.editors_choice,
        R.drawable.creative,
        R.drawable.quiet_calm,
        R.drawable.top_charts,
        R.drawable.trending,
        R.drawable.lifestyle
    )

    private lateinit var list: ArrayList<String>

    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageSlider= findViewById(R.id.image_slider)
        natureLayout= findViewById(R.id.nature_layout)
        wildlifeLayout= findViewById(R.id.wildlife_layout)
        neonArtLayout= findViewById(R.id.neon_art_layout)
        vehiclesLayout= findViewById(R.id.vehicles_layout)
        homeRv= findViewById(R.id.home_rv)
        progressBar= findViewById(R.id.progress_bar)


        list= ArrayList()

        firebaseDatabase= FirebaseDatabase.getInstance()

        setUpSliderRecyclerView()

        natureLayout.setOnClickListener {

            val intent= Intent(this,CategoryActivity::class.java)

            intent.putExtra("categoryId","nature")
            intent.putExtra("categoryName", "Nature")

            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)

        }

        wildlifeLayout.setOnClickListener {

            val intent= Intent(this,CategoryActivity::class.java)

            intent.putExtra("categoryId","wildlife")
            intent.putExtra("categoryName", "Wildlife")

            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)

        }

        neonArtLayout.setOnClickListener {

            val intent= Intent(this,CategoryActivity::class.java)

            intent.putExtra("categoryId","neonart")
            intent.putExtra("categoryName", "Neon & Art")

            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)

        }

        vehiclesLayout.setOnClickListener {

            val intent= Intent(this,CategoryActivity::class.java)

            intent.putExtra("categoryId","vehicles")
            intent.putExtra("categoryName", "Vehicles")

            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

            startActivity(intent)

        }

        val wallpaperAdapter= WallpaperAdapter(list,this)
        val layoutManager= GridLayoutManager(this,2)

        homeRv.layoutManager= layoutManager

        homeRv.adapter= wallpaperAdapter

        firebaseDatabase.reference.child("wallpapers").child("home").addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){

                    for (dataSnapshot in snapshot.children){

                        val data= dataSnapshot.value.toString()

                        list.add(data)

                    }

                    wallpaperAdapter.notifyDataSetChanged()

                    homeRv.adapter= wallpaperAdapter

                    progressBar.visibility= View.GONE

                }

            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()

            }


        })



    }

    private fun setUpSliderRecyclerView(){

        val sliderAdapter= SliderAdapter(images,this)
        imageSlider.setSliderAdapter(sliderAdapter)
        imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        imageSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        imageSlider.startAutoCycle()


    }

}