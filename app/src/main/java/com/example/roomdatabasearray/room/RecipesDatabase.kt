package com.example.roomdatabasearray.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.roomdatabasearray.ui.models.PostApiPhotoResponse
import com.example.roomdatabasearray.ui.models.PostApiResponseItem
import com.example.roomdatabasearray.ui.models.RegisterTableResponse
import com.example.roomdatabasearray.utils.ImageConverter


@Database(entities = [PostApiResponseItem::class, PostApiPhotoResponse::class,RegisterTableResponse::class], version = 3)
@TypeConverters(ImageConverter::class)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}
