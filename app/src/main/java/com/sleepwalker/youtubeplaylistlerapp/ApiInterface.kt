package com.sleepwalker.youtubeplaylistlerapp

import PlaylistData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface ApiInterface {

   // https://youtube.googleapis.com/youtube/v3/playlists?part=snippet&channelId=UCpHsUAp8xSRGhxDhcZl2abA&maxResults=100&key=[YOUR_API_KEY]

    @GET("playlists?part=snippet")

    fun  tumListeleriGetir(@Query("channelId") channelID:String, @Query("key") apiKey:String, @Query("maxResults") limit:Int):Call<PlaylistData>



}