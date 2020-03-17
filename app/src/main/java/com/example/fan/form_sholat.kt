package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_form_marquee.*
import kotlinx.android.synthetic.main.activity_form_sholat.*
import kotlinx.android.synthetic.main.activity_form_slideshow.*
import org.json.JSONArray

class form_sholat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_sholat)
        val context: form_sholat = this

        kembali_sholat.setOnClickListener {
            val intent = Intent(context, MainActivity::class.java)

            startActivity(intent)
            finish()
        }
        update_sholat.setOnClickListener{

            var data_shubuh :String = jadwal_shubuh.text.toString()
            var data_dhuhur :String = jadwal_dhuhur.text.toString()
            var data_ashar :String = jadwal_ashar.text.toString()
            var data_maghrib :String = jadwal_maghrib.text.toString()
            var data_isha :String = jadwal_isya.text.toString()
            var data_dhuha :String = jadwal_dhuha.text.toString()


            postkeserver(data_shubuh,data_dhuhur,data_ashar,data_maghrib,data_isha,data_dhuha)

            val intent = Intent(context,MainActivity::class.java)
            startActivity(intent)

        }
    }
    fun postkeserver(jadwal_shubuh:String,jadwal_dhuhur:String,jadwal_ashar:String,jadwal_maghrib:String,jadwal_isya:String,jadwal_dhuha:String){
        AndroidNetworking.post("https://mobileterapan.000webhostapp.com/update-sholat.php")
            .addBodyParameter("shubuh",jadwal_shubuh)
            .addBodyParameter("dhuhur",jadwal_dhuhur)
            .addBodyParameter("ashar",jadwal_ashar)
            .addBodyParameter("maghrib",jadwal_maghrib)
            .addBodyParameter("isha",jadwal_isya)
            .addBodyParameter("dhuha",jadwal_dhuha)
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
