package com.example.roomdatabasearray.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdatabasearray.R
import com.example.roomdatabasearray.remote.RemoteService
import com.example.roomdatabasearray.remote.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import javax.inject.Inject


@HiltViewModel
class PostViewModel @Inject constructor(private val workService: RemoteService) : ViewModel() {

    private var _getPosAPIResponse = MutableLiveData<Resource<Any>>()
    var getPostAPIResponse: LiveData<Resource<Any>> = _getPosAPIResponse

    fun callGetAllPostAPI(context: Context) =
        viewModelScope.launch {
            _getPosAPIResponse.value = Resource.Loading()
            try {
                val response = workService.getAllPostData()
                if (response.isSuccessful){
                    var araay = JSONArray(response.body())
                    Log.d("RsponseDataUser11","${araay}")
                    Log.d("RsponseDataUser22","${response.body()}")
                   // recipesDatabase.recipesDao().insertAllPois(response.body()!!.listPostData)

                    _getPosAPIResponse.value = Resource.Success(response.body()!!)
                    //recipesDatabase.recipesDao().insertAllPois(response.body()!!.listPostData)
                }
                else {
                    _getPosAPIResponse.value =
                        Resource.Error(response.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _getPosAPIResponse.value =
                    Resource.Error(e.message ?: context.getString(R.string.unexpected_error))
            }
        }
}