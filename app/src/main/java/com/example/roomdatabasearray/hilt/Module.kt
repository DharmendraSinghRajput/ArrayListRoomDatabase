package com.example.roomdatabasearray.hilt

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.mvvmhiltcoroutineroom.remote.APIConst
import com.example.roomdatabasearray.R
import com.example.roomdatabasearray.remote.RemoteService
import com.example.roomdatabasearray.room.RecipesDao
import com.example.roomdatabasearray.room.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideService(): RemoteService {
        var retrofit: Retrofit? = null
        if (retrofit != null)
            return retrofit.create(RemoteService::class.java)

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.connectTimeout(50, TimeUnit.MINUTES)
        okHttpClientBuilder.writeTimeout(50, TimeUnit.MINUTES)
        okHttpClientBuilder.readTimeout(50, TimeUnit.MINUTES)

        okHttpClientBuilder.addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        okHttpClientBuilder.addInterceptor(Interceptor { chain ->
            val request = chain.request().newBuilder()
                .header("Content-Type", "application/json")
                .build()
            chain.proceed(request)
        })

        retrofit = Retrofit.Builder().client(okHttpClientBuilder.build())
            .baseUrl(APIConst.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit!!.create(RemoteService::class.java)
    }



    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): RecipesDatabase {
        return Room.databaseBuilder(
            context,
            RecipesDatabase::class.java,
            "PostDataBase"
        ).build()
    }
    @Provides
    fun providesUserDao(userDatabase: RecipesDatabase): RecipesDao =userDatabase.recipesDao()
    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences = context.getSharedPreferences("${context.getString(
        R.string.app_name)}_PREF", Context.MODE_PRIVATE)


  /*  @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(context, RecipesDatabase::class.java, "RECIPES_DATABASE_NAME").build()

    @Singleton
    @Provides
    fun provideDao(database: RecipesDatabase) =    database.recipesDao()*/
}