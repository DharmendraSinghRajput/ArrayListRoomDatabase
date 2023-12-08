package com.example.roomdatabasearray.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdatabasearray.ui.models.PostApiPhotoResponse
import com.example.roomdatabasearray.ui.models.PostApiResponseItem


@Database(entities = [PostApiResponseItem::class, PostApiPhotoResponse::class], version = 1)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao
}
