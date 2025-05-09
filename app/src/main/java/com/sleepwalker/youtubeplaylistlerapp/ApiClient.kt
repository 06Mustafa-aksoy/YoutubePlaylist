package com.sleepwalker.youtubeplaylistlerapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val Base_URL = "https://youtube.googleapis.com/youtube/v3/"

    private var retrofit : Retrofit? = null

    val client : Retrofit?

        get() {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(Base_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            }

            return retrofit
        }




}