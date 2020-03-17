package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_form_marquee.*
import kotlinx.android.synthetic.main.activity_form_pengumuman.*
import kotlinx.android.synthetic.main.activity_form_slideshow.*
import org.json.JSONArray

class form_slideshow : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_slideshow)
        val context: form_slideshow = this

        kembali_slideshow.setOnClickListener {
            val intent = Intent(context, activity_slideshow::class.java)

            startActivity(intent)
            finish()
        }
        update_slideshow.setOnClickListener{

            var data_juduls :String = slideshow_judul.text.toString()
            var data_url :String = slideshow_url.text.toString()

            postkeserver(data_juduls, data_url)

            val intent = Intent(context,activity_slideshow::class.java)
            startActivity(intent)

        }
    }
    fun postkeserver(slideshow_judul:String,slideshow_url:String){
        AndroidNetworking.post("https://mobileterapan.000webhostapp.com//update-slideshow.php")
            .addBodyParameter("judul_slideshow",slideshow_judul)
            .addBodyParameter("url_slideshow",slideshow_url)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {

                }

                override fun onError(anError: ANError) {

                }
            })
    }
}
