package com.sleepwalker.youtubeplaylistlerapp

import PlaylistAdapter
import PlaylistData
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val API_KEY = "AIzaSyCF9XfW85XyCu19cr9VN_TR9BuTB1PysyA"
    private val CHANNEL_ID = "UCpHsUAp8xSRGhxDhcZl2abA"
    var gelenVeri : PlaylistData?  = null
    var oynatmaListeleri : List<PlaylistData.Items>? = null
    var myAdapter : PlaylistAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var apiInterface = ApiClient.client?.create(ApiInterface ::class.java)

       var apiCall = apiInterface?.tumListeleriGetir(CHANNEL_ID,API_KEY,50)

        apiCall?.enqueue(object: Callback<PlaylistData> {
            override fun onResponse(call: Call<PlaylistData>, response: Response<PlaylistData>) {

                Log.e("BASARILI", "" +call.request()?.url()?.toString())

                gelenVeri = response.body()

                oynatmaListeleri = gelenVeri?.items

                myAdapter  = PlaylistAdapter(oynatmaListeleri)

                val myadapter = myAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>

                val myViewList  = findViewById<RecyclerView>(R.id.recyclerViewPlaylist)

                myViewList.adapter = myadapter

                var myLayoutManager=LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.VERTICAL,false)

                myViewList.layoutManager=myLayoutManager

                supportActionBar?.setSubtitle("Toplam Liste :"+oynatmaListeleri?.size)

                Log.e("BASARILI","TOPLAM LİSTE SAYISI"+gelenVeri?.pageInfo?.totalResults)


                /*

                for (i in 0..response.body()?.items?.size!!-1)

                Log.e("BASARILI", "" +response.body()?.items?.get(i)?.snippet?.title.toString())

                */

            }

            override fun onFailure(call: Call<PlaylistData>, t: Throwable) {

                Log.e("HATA", ""+t.printStackTrace())

            }

        })

    }

}