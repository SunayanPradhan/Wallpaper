package com.technosp.wallpaper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryTitle: TextView
    private lateinit var categoryRv: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var list: ArrayList<String>
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        categoryTitle= findViewById(R.id.category_title)
        categoryRv= findViewById(R.id.category_rv)
        progressBar= findViewById(R.id.progress_bar)
        list= ArrayList()
        firebaseDatabase= FirebaseDatabase.getInstance()

        val categoryId= intent.getStringExtra("categoryId").toString()
        val categoryName= intent.getStringExtra("categoryName").toString()

        categoryTitle.text= categoryName


        val wallpaperAdapter= WallpaperAdapter(list,this)
        val layoutManager= GridLayoutManager(this,2)

        categoryRv.layoutManager= layoutManager

        categoryRv.adapter= wallpaperAdapter

        firebaseDatabase.reference.child("wallpapers").child(categoryId).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (dataSnapshot in snapshot.children){

                        val data= dataSnapshot.value.toString()

                        list.add(data)

                    }

                    wallpaperAdapter.notifyDataSetChanged()

                    categoryRv.adapter= wallpaperAdapter

                    progressBar.visibility= View.GONE

                }
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@CategoryActivity, "Error", Toast.LENGTH_SHORT).show()

            }


        })



    }
}