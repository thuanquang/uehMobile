package com.example.buoi_8_1_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.buoi_8_1_retrofit.model.Post
import com.example.buoi_8_1_retrofit.repository.APIPostRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel(private val postRepository: APIPostRepository) : ViewModel() {
    private val _responseValue: MutableLiveData<Response<Post>> = MutableLiveData()
    val responseValue: LiveData<Response<Post>> = _responseValue

    private val _postsResponse: MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val postsResponse: LiveData<Response<List<Post>>> = _postsResponse

    fun getPostItem() {
        viewModelScope.launch {
            val response = postRepository.getPostItem()
            _responseValue.value = response
        }
    }

    fun getPosts() {
        viewModelScope.launch {
            val response = postRepository.getPosts()
            _postsResponse.value = response
        }
    }
}


