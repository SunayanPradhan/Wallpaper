package com.technosp.wallpaper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter(private val images:IntArray, private val context: Context):SliderViewAdapter<SliderAdapter.Holder>() {

    class Holder(itemView: View): ViewHolder(itemView) {

        val sliderImage: ImageView= itemView.findViewById(R.id.slider_image)

    }

    override fun getCount(): Int {

        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): Holder {
        val view= LayoutInflater.from(parent?.context).inflate(R.layout.slider_layout, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(viewHolder: Holder?, position: Int) {

        val currentItem= images[position]

        Glide.with(context.applicationContext).load(currentItem).into(viewHolder?.sliderImage!!)

        viewHolder.itemView.setOnClickListener {

            when(true){

                (position==0) ->{

                    val intent= Intent(context,CategoryActivity::class.java)

                    intent.putExtra("categoryId","editorschoice")
                    intent.putExtra("categoryName", "Editors' Choice")

                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

                    context.startActivity(intent)
                }

                (position==1) ->{

                    val intent= Intent(context,CategoryActivity::class.java)

                    intent.putExtra("categoryId","creative")
                    intent.putExtra("categoryName", "Creative")

                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

                    context.startActivity(intent)
                }

                (position==2) ->{

                    val intent= Intent(context,CategoryActivity::class.java)

                    intent.putExtra("categoryId","quietcalm")
                    intent.putExtra("categoryName", "Quiet & Calm")

                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

                    context.startActivity(intent)
                }

                (position==3) ->{

                    val intent= Intent(context,CategoryActivity::class.java)

                    intent.putExtra("categoryId","topcharts")
                    intent.putExtra("categoryName", "Top Charts")

                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

                    context.startActivity(intent)
                }

                (position==4) ->{

                    val intent= Intent(context,CategoryActivity::class.java)

                    intent.putExtra("categoryId","trending")
                    intent.putExtra("categoryName", "Trending")

                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

                    context.startActivity(intent)
                }

                (position==5) ->{

                    val intent= Intent(context,CategoryActivity::class.java)

                    intent.putExtra("categoryId","lifestyle")
                    intent.putExtra("categoryName", "Lifestyle")

                    intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK

                    context.startActivity(intent)
                }


                else -> {

                    Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT).show()

                }
            }

        }

    }
}