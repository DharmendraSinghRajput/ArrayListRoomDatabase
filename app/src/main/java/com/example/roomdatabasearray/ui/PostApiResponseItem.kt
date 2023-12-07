package com.example.roomdatabasearray.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class PostApiResponseItem(
    val userId: Long,
    @PrimaryKey
    val id: Long,
    val title: String,
    val body: String,
)