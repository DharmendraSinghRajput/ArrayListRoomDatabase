package com.example.roomdatabasearray.ui.models

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_register")
data class RegisterTableResponse (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String="",
    val contact_number: String ="",
    val email: String ="",
    val password: String ="",
    @ColumnInfo(name = "image", typeAffinity = ColumnInfo.BLOB)
    val image: Bitmap
)