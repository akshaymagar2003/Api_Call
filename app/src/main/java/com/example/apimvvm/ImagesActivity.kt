package com.example.apimvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apimvvm.databinding.ActivityImagesBinding


class ImagesActivity : AppCompatActivity() {
    lateinit var binding : ActivityImagesBinding

    private lateinit var imagesViewModel: ImagesViewModel
    private lateinit var imagesAdapter: ImagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imagesAdapter = ImagesAdapter()
        binding.imagesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ImagesActivity)
            adapter = imagesAdapter
        }

        imagesViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)
        imagesViewModel.images.observe(this, { images ->
            imagesAdapter.setImages(images)
        })
    }
}
