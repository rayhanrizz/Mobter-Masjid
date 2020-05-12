package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_marquee.*
import org.json.JSONObject

class activity_marquee : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee)

        val context: activity_marquee = this

        backmarquee.setOnClickListener {
            val intent = Intent(context, menu::class.java)

            startActivity(intent)
            finish()
        }
        formmarquee.setOnClickListener {
            val intent = Intent(context, form_marquee::class.java)

            startActivity(intent)
            finish()
        }

        getdarisever()
    }
    fun  getdarisever(){
        AndroidNetworking.get("https://mobileterapan.000webhostapp.com/marquee.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTitle",jsonObject.optString("isi_marquee"))


                        isi_marquee.setText(jsonObject.optString("isi_marquee"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
