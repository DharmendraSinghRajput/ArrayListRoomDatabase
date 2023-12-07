package com.example.roomdatabasearray.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdatabasearray.ui.PostApiResponseItem


@Database(entities = [PostApiResponseItem::class], version = 6)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao

}
