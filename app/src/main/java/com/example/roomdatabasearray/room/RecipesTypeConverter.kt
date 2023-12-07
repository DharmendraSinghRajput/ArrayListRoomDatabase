/*

package com.example.roomdatabasearray.room

import androidx.room.TypeConverter
import com.example.roomdatabasearray.ui.PostModelResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipesTypeConverter {

   @TypeConverter
    fun fromGroupTaskMemberList(value: List<PostModelResponse>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<PostModelResponse>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGroupTaskMemberList(value: String): List<PostModelResponse> {
        val gson = Gson()
        val type = object : TypeToken<List<PostModelResponse>>() {}.type
        return gson.fromJson(value, type)
    }

}
*/
