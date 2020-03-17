package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val context: menu = this

        sholat.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)

            startActivity(intent)
            finish()
        }

        identitas_masjid.setOnClickListener {
            val intent = Intent(context, identitas::class.java)

            startActivity(intent)
            finish()
        }

        marquee.setOnClickListener {
            val intent = Intent(context, activity_marquee::class.java)

            startActivity(intent)
            finish()
        }

        pengumuman.setOnClickListener {
            val intent = Intent(context, activity_pengumuman::class.java)

            startActivity(intent)
            finish()
        }

        slideshow.setOnClickListener {
            val intent = Intent(context, activity_slideshow::class.java)

            startActivity(intent)
            finish()
        }

        tagline.setOnClickListener {
            val intent = Intent(context, activity_tagline::class.java)

            startActivity(intent)
            finish()
        }
    }
}
