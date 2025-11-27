package com.example.buoi_8_1_retrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.buoi_8_1_retrofit.repository.APIPostRepository

class PostViewModelFactory(private val postRepository: APIPostRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PostViewModel(postRepository) as T
    }
}


