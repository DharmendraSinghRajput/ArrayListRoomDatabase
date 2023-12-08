package com.example.roomdatabasearray.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasearray.R
import com.example.roomdatabasearray.remote.RemoteService
import com.example.roomdatabasearray.remote.Resource
import com.example.roomdatabasearray.room.RecipesDao
import com.example.roomdatabasearray.ui.models.PostApiPhotoResponse
import com.example.roomdatabasearray.ui.models.PostApiResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(private val workService: RemoteService, var recipesDao:RecipesDao) : ViewModel() {

    private var _getPosAPIResponse = MutableLiveData<Resource<Any>>()
    var postResponseData =MutableLiveData<List<PostApiResponseItem>>()
    var postPhotoResponse =MutableLiveData<List<PostApiPhotoResponse>>()

    fun callGetAllPostAPI(context: Context) =
        viewModelScope.launch {
            _getPosAPIResponse.value = Resource.Loading()
            try {
                val response = workService.getAllPostData()
                if (response.isSuccessful) {
                    _getPosAPIResponse.value = Resource.Success(response.body()!!)
                    insert(response.body()!!)
                } else {
                    _getPosAPIResponse.value =
                        Resource.Error(response.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _getPosAPIResponse.value =
                    Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
            }
        }

    fun callGetAllPhotoAPI(context: Context) =
        viewModelScope.launch {
            _getPosAPIResponse.value = Resource.Loading()
            try {
                val response = workService.getAllPhotoData()
                if (response.isSuccessful) {
                    insertPhoto(response.body()!!)
                } else {
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _getPosAPIResponse.value =
                    Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
            }
        }

    suspend fun insert(postApiResponseItem: List<PostApiResponseItem>) =
        withContext(Dispatchers.IO) {
            recipesDao.insertAllPois(postApiResponseItem)
        }

    suspend fun insertPhoto(postApiPhotoResponse: List<PostApiPhotoResponse>) =
        withContext(Dispatchers.IO) {
            recipesDao.insertPhoto(postApiPhotoResponse)
        }

    init {
        viewModelScope.launch {
            recipesDao.getUserDataProfile().collect {
                postResponseData.postValue(it)
            }

        }
        viewModelScope.launch {
            recipesDao.getUserPhotoResponse().collect {
                postPhotoResponse.postValue(it)
            }
        }
    }
}