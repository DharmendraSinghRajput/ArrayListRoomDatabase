package com.example.roomdatabasearray.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabasearray.ui.adapter.PostAdapter
import com.example.roomdatabasearray.databinding.ActivityMainBinding
import com.example.roomdatabasearray.ui.viewmodel.PostViewModel
import com.example.roomdatabasearray.utils.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val mBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    val mainViewModel: PostViewModel by viewModels()
    private val postAdapter by lazy {
        PostAdapter { position ->

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            btnNextScreen.setOnClickListener {
                startActivity(Intent(this@MainActivity,HomeActivity::class.java))
            }
            mainViewModel.callGetAllPostAPI(this@MainActivity)
            mainViewModel.postResponseData.observe(this@MainActivity){
                    postAdapter.setData(it)
                }

            listRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = postAdapter
            }

        }

    }

}