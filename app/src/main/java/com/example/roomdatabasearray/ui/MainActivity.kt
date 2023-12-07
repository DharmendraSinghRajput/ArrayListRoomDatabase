package com.example.roomdatabasearray.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.roomdatabasearray.databinding.ActivityMainBinding
import com.example.roomdatabasearray.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val mainViewModel: PostViewModel by viewModels()
    var apiResponseData:PostApiResponseItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            mainViewModel.callGetAllPostAPI(this@MainActivity)
            mainViewModel.getPostAPIResponse.observe(this@MainActivity){
                Log.d("ResponseData1","${it}")
                if (it!=null){
                    showToast(it.message.toString())
                }

            }

        }
    }

}