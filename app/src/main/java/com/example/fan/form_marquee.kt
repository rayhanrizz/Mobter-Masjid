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

class form_marquee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_marquee)
        val context: form_marquee = this

        kembali_marquee.setOnClickListener {
            val intent = Intent(context, activity_marquee::class.java)

            startActivity(intent)
            finish()
        }
        update_marque.setOnClickListener{

            var data_isimarquee :String = marque_isi.text.toString()

            postkeserver(data_isimarquee)

            val intent = Intent(context,activity_marquee::class.java)
            startActivity(intent)

        }
    }
    fun postkeserver(marque_isi:String){
        AndroidNetworking.post("https://mobileterapan.000webhostapp.com/update-marquee.php")
            .addBodyParameter("isi_marquee",marque_isi)
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
