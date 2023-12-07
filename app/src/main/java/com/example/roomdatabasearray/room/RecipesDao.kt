
package com.example.roomdatabasearray.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.roomdatabasearray.ui.PostApiResponseItem

@Dao
interface RecipesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(getAllPostResponse: List<PostApiResponseItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPois(poisRoomList: ArrayList<PostApiResponseItem>)

 /*   @Query("SELECT * FROM data")
    fun readRecipes(): LiveData<List<PostModelResponse>>*/

}
