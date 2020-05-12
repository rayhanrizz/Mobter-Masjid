package com.example.fan

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_marquee.*
import kotlinx.android.synthetic.main.activity_slideshow.*
import org.json.JSONObject

class activity_slideshow : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)
        val context: activity_slideshow = this

        backslideshow.setOnClickListener {
            val intent = Intent(context, menu::class.java)

            startActivity(intent)
            finish()
        }
//        formslideshow.setOnClickListener {
//            val intent = Intent(context, form_slideshow::class.java)
//
//            startActivity(intent)
//            finish()
//        }

        val recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager= LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val users=ArrayList<User>()

        AndroidNetworking.get("https://mobileterapan.000webhostapp.com//slideshow.php")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    Log.e("_kotlinResponse", response.toString())

                    val jsonArray = response.getJSONArray("result")
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        Log.e("_kotlinTitle", jsonObject.optString("judul_slideshow"))

                        // txt1.setText(jsonObject.optString("shubuh"))
                        var isi1=jsonObject.optString("judul_slideshow").toString()
                        var isi2=jsonObject.optString("url_slideshow").toString()

                        users.add(User("$isi1", "$isi2"))
                    }

                    val adapter=CustomAdapter(users)
                    recyclerView.adapter=adapter
                }
                override fun onError(anError: ANError) {
                    Log.i("_err", anError.toString())
                }
            })
    }
}
