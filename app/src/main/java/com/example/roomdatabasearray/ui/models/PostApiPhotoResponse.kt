package com.example.roomdatabasearray.ui.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_image")
class PostApiPhotoResponse (
        val albumId: Int,
        @PrimaryKey
        val id: Int,
        val thumbnailUrl: String,
        val title: String,
        val url: String

)