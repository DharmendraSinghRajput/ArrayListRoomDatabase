package com.example.roomdatabasearray.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.roomdatabasearray.databinding.ActivityLoginBinding
import com.example.roomdatabasearray.ui.viewmodel.PostViewModel
import com.example.roomdatabasearray.utils.BaseActivity
import com.example.roomdatabasearray.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : BaseActivity() {
    private val mBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val mainViewModel: PostViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity,RegisterActivity::class.java))
            }
            mainViewModel.getRegisterResponse.observe(this@LoginActivity){
                if (it!=null){
                    showToast(it.toString())
                }
            }

            buttonSave.setOnClickListener {
                val email=editUserEmail.text.toString().trim()
                val password=editUserPassword.text.toString().trim()
                if(email.isNotEmpty() && password.isNotEmpty()) {
                    CoroutineScope(Dispatchers.Main).launch {
                        var responseData = mainViewModel.loginUser(email, password)
                        if (responseData != null) {
                            startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                        } else {
                            showToast("not login user")

                        }

                    }
                }else{
                    showToast("Please fill all fields")
                }

            }
        }
    }
}