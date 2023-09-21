package com.technosp.wallpaper

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class SetWallpaperActivity : AppCompatActivity() {

    private lateinit var setImage: ImageView

    private lateinit var setButton: Button

    private lateinit var decorView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_wallpaper)

        setImage= findViewById(R.id.set_image)
        setButton= findViewById(R.id.set_button)
        decorView= window.decorView

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)

        supportActionBar?.hide()

        decorView.setOnSystemUiVisibilityChangeListener { visibility->

        if (visibility==0){

            decorView.systemUiVisibility= hideSystemBars()

        }


        }

        val imageUrl= intent.getStringExtra("imageUrl").toString()

        Glide.with(applicationContext).load(imageUrl).into(setImage)

        setButton.setOnClickListener {

            setButton.visibility= View.INVISIBLE

            if (setButton.visibility== View.INVISIBLE){

                val bitmap= (setImage.drawable as BitmapDrawable).bitmap
                val manager= WallpaperManager.getInstance(applicationContext)
                manager.setBitmap(bitmap)

                Toast.makeText(this, "Successfully Set", Toast.LENGTH_SHORT).show()

                setButton.visibility= View.VISIBLE

                setButton.text= "Applied"

                setButton.isEnabled= false

            }
            else{

                setButton.isEnabled= true
            }

        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){

            decorView.systemUiVisibility= hideSystemBars()
        }

    }


    private fun hideSystemBars(): Int{

        return (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
                or View.SYSTEM_UI_FLAG_VISIBLE
                )

    }

}