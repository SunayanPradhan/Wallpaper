package com.technosp.wallpaper

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class WallpaperAdapter(private val list: ArrayList<String>, private val context: Context): RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val wallpaperImage: ImageView= itemView.findViewById(R.id.wallpaper_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.wallpaper_layout,parent, false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem= list[position]

        Glide.with(context.applicationContext).load(currentItem).into(holder.wallpaperImage)

        holder.itemView.setOnClickListener {

            val intent= Intent(context, SetWallpaperActivity::class.java)
            intent.putExtra("imageUrl",currentItem)
            intent.flags= Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)

        }

    }
}