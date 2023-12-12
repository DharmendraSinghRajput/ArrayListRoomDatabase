package com.example.roomdatabasearray.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.roomdatabasearray.R
import com.example.roomdatabasearray.databinding.ActivitySplashBinding
import com.example.roomdatabasearray.utils.BaseActivity
import com.example.roomdatabasearray.utils.PrefUtil

class SplashActivity : BaseActivity() {
    val mBinding by lazy { ActivitySplashBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
         /*   Handler(Looper.getMainLooper()).postDelayed({
                prefUtil.getString(PrefUtil.LOGIN_DATA)?.let {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java).apply {
                        finish()
                    })

                } ?: startActivity(Intent(this@SplashActivity, LoginActivity::class.java).apply {
                    finish()
                })
            }, 2000)*/
        }
    }
}