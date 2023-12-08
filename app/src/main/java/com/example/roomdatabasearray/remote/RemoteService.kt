
package com.example.roomdatabasearray.remote

import com.example.roomdatabasearray.ui.models.PostApiPhotoResponse
import com.example.roomdatabasearray.ui.models.PostApiResponseItem
import retrofit2.Response
import retrofit2.http.GET


interface RemoteService {
    @GET("posts")
    suspend fun getAllPostData(): Response<List<PostApiResponseItem>>

    @GET("photos")
    suspend fun getAllPhotoData(): Response<List<PostApiPhotoResponse>>
}