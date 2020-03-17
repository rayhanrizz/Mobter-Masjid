package com.example.fan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.fan.identitas
import kotlinx.android.synthetic.main.activity_identitas.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_menu.*
import org.json.JSONArray
import org.json.JSONObject

class identitas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_identitas)

        val context: identitas = this

        backindentitas.setOnClickListener {
            val intent = Intent(context, menu::class.java)
            startActivity(intent)
            finish()
        }
        formidentitas.setOnClickListener {
            val intent = Intent(context, form_identitas::class.java)
            startActivity(intent)
            finish()
        }

        getdarisever()
    }
    fun  getdarisever(){
        AndroidNetworking.get("https://mobileterapan.000webhostapp.com/identitas_masjid.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject){
                    Log.e("kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("kotlinTitle",jsonObject.optString("nama_masjid"))
                        Log.e("kotlinTitle",jsonObject.optString("alamat_masjid"))


                        nama.setText(jsonObject.optString("nama_masjid"))
                        alamat.setText(jsonObject.optString("alamat_masjid"))
                    }
                }

                override fun onError(anError: ANError?) {
                    Log.i("_err", anError.toString())
                }
            })
    }
//    fun postkeserver(data1:String,data2:String){
//        AndroidNetworking.post("http://192.168.100.26/androidterapan/proses-identitas.php")
//            .addBodyParameter("nama_masjid",data1)
//            .addBodyParameter("alamat_masjid",data2)
//            .setPriority(Priority.MEDIUM)
//            .build()
//            .getAsJSONArray(object : JSONArrayRequestListener{
//                override fun onResponse(response: JSONArray) {
//
//                }
//
//                override fun onError(anError: ANError) {
//
//                }
//            })
//    }
}
