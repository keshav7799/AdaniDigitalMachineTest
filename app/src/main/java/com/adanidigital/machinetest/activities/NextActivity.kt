package com.adanidigital.machinetest.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.adanidigital.machinetest.databinding.ActivityNextBinding
import com.bumptech.glide.Glide


class NextActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNextBinding

    var imageview: ImageView? = null
    var description:TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.adanidigital.machinetest.R.layout.activity_next)



       // val descriptionTxt = intent.extras!!.getString("Description", "")
       // val imgURL = intent.extras!!.getString("Image", "")
        val descriptionTxt = intent.extras!!.getString("Description")
        val imgURL = intent.extras!!.getString("Image")
        Glide.with(applicationContext).load(imgURL)
            .placeholder(com.adanidigital.machinetest.R.drawable.ic_launcher_background)
            .error(com.adanidigital.machinetest.R.drawable.ic_launcher_background)
            .into(imageview!!)

        description!!.setText(descriptionTxt);
    }
}