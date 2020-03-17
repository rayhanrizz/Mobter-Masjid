package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import kotlinx.android.synthetic.main.activity_form_marquee.*
import kotlinx.android.synthetic.main.activity_form_tagline.*
import org.json.JSONArray

class form_tagline : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_tagline)
        val context: form_tagline = this

        kembali_tagline.setOnClickListener {
            val intent = Intent(context, activity_tagline::class.java)

            startActivity(intent)
            finish()
        }
        update_tagline.setOnClickListener{

            var data_isitagline :String = tagline_isi.text.toString()

            postkeserver(data_isitagline)

            val intent = Intent(context,activity_tagline::class.java)
            startActivity(intent)

        }
    }
    fun postkeserver(tagline_isi:String){
        AndroidNetworking.post("https://mobileterapan.000webhostapp.com/update-tagline.php")
            .addBodyParameter("isi_tagline",tagline_isi)
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
