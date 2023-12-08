package com.example.roomdatabasearray.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasearray.R
import com.example.roomdatabasearray.databinding.ActivityHomeBinding
import com.example.roomdatabasearray.ui.adapter.PostAdapter
import com.example.roomdatabasearray.ui.adapter.PostPhotoAdapter
import com.example.roomdatabasearray.ui.viewmodel.PostViewModel
import com.example.roomdatabasearray.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    val mBinding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    val mainViewModel: PostViewModel by viewModels()
    val postPhotoAdapter by lazy {
        PostPhotoAdapter{ position ->
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            mainViewModel.callGetAllPhotoAPI(this@HomeActivity)
            mainViewModel.postPhotoResponse.observe(this@HomeActivity){
                    postPhotoAdapter.setData(it)

            }
            listRecyclerView.apply {
                layoutManager=LinearLayoutManager(this@HomeActivity)
                adapter=postPhotoAdapter
            }

        }
    }
}