package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_marquee.*
import kotlinx.android.synthetic.main.activity_pengumuman.*
import org.json.JSONObject

class activity_pengumuman : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengumuman)

        val context: activity_pengumuman = this

        backpengumuman.setOnClickListener {
            val intent = Intent(context, menu::class.java)

            startActivity(intent)
            finish()
        }
        formpengumuman.setOnClickListener {
            val intent = Intent(context, form_pengumuman::class.java)

            startActivity(intent)
            finish()
        }

        getdarisever()
    }
    fun  getdarisever(){
        AndroidNetworking.get("https://mobileterapan.000webhostapp.com/pengumuman_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTitle",jsonObject.optString("judul_pengumuman"))
                        Log.e("kotlinTitle",jsonObject.optString("isi_pengumuman"))


                        judul_pengumuman.setText(jsonObject.optString("judul_pengumuman"))
                        isi_pengumuman.setText(jsonObject.optString("isi_pengumuman"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
