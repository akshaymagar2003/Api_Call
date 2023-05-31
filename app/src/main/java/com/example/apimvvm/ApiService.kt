package com.example.apimvvm

import com.example.apimvvm.dataClass.Photo
import retrofit2.Call
import retrofit2.http.GET




interface ApiService {
    @GET("photos")
     fun getPhotos(): Call<List<Photo>>
}