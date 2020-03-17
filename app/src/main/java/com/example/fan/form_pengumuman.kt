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
import org.json.JSONArray

class form_pengumuman : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pengumuman)
        val context: form_pengumuman = this

        kembali_pengumuman.setOnClickListener {
            val intent = Intent(context, activity_pengumuman::class.java)

            startActivity(intent)
            finish()
        }
        update_pengumuman.setOnClickListener{

            var data_judul :String = pengumuman_judul.text.toString()
            var data_isi :String = pengumuman_isi.text.toString()

            postkeserver(data_judul, data_isi)

            val intent = Intent(context,activity_pengumuman::class.java)
            startActivity(intent)

        }
    }
    fun postkeserver(pengumuman_judul:String,pengumuman_isi:String){
        AndroidNetworking.post("https://mobileterapan.000webhostapp.com/update-pengumuman.php")
            .addBodyParameter("judul_pengumuman",pengumuman_judul)
            .addBodyParameter("isi_pengumuman",pengumuman_isi)
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
