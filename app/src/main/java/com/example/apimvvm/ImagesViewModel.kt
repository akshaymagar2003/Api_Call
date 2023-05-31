package com.example.apimvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apimvvm.dataClass.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ImagesViewModel : ViewModel() {

    private val _images = MutableLiveData<List<String>>()
    val images: LiveData<List<String>> get() = _images

    init {
        loadImages()
    }

    private fun loadImages() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.getPhotos()

        call.enqueue(object : Callback<List<Photo>> {
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    val photoList = response.body()
                    val imageUrls = photoList?.map { it.url }
                    _images.value = imageUrls
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                // Handle API call failure
            }
        })
    }
}
