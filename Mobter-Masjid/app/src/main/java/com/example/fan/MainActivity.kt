package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context: MainActivity = this

        backsholat.setOnClickListener {
            val intent = Intent(context, menu::class.java)

            startActivity(intent)
            finish()
        }
        formsholat.setOnClickListener {
            val intent = Intent(context, form_sholat::class.java)

            startActivity(intent)
            finish()
        }

        getdarisever()
    }

    fun  getdarisever(){
        AndroidNetworking.get("https://mobileterapan.000webhostapp.com/contoh_jadwal_json.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener{
                override fun onResponse(response: JSONObject){
                    Log.e("kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTitle",jsonObject.optString("shubuh"))
                        Log.e("kotlinTitle",jsonObject.optString("dhuhur"))
                        Log.e("kotlinTitle",jsonObject.optString("ashar"))
                        Log.e("kotlinTitle",jsonObject.optString("maghrib"))
                        Log.e("kotlinTitle",jsonObject.optString("isha"))
                        Log.e("kotlinTitle",jsonObject.optString("dhuha"))


                        txt1.setText(jsonObject.optString("shubuh"))
                        txt2.setText(jsonObject.optString("dhuhur"))
                        ashar.setText(jsonObject.optString("ashar"))
                        txt4.setText(jsonObject.optString("maghrib"))
                        txt5.setText(jsonObject.optString("isha"))
                        txt6.setText(jsonObject.optString("dhuha"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
