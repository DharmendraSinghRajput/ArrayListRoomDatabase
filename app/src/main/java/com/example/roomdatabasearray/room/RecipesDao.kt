
package com.example.roomdatabasearray.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomdatabasearray.ui.models.PostApiPhotoResponse
import com.example.roomdatabasearray.ui.models.PostApiResponseItem
import com.example.roomdatabasearray.ui.models.RegisterTableResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAllPois(poisRoomList:List<PostApiResponseItem>)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPhoto(postApiPhotoResponse:List<PostApiPhotoResponse>)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun registerUser(registerTableResponse: RegisterTableResponse)

    @Query("SELECT * FROM user_register WHERE email LIKE :email AND password LIKE :password")
    suspend fun getUserEmail(email: String,password:String): RegisterTableResponse

    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUserDataProfile():Flow<List<PostApiResponseItem>>

    @Query("SELECT * FROM user_image ORDER BY id ASC")
    fun getUserPhotoResponse():Flow<List<PostApiPhotoResponse>>

    @Query("SELECT * FROM user_register ORDER BY id ASC")
    fun getUserRegister():Flow<List<RegisterTableResponse>>

}
