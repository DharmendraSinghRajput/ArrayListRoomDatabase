package com.example.roomdatabasearray.utils

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.roomdatabasearray.R
import com.example.roomdatabasearray.remote.Resource
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
open class BaseActivity(): AppCompatActivity(){
    private lateinit var loader: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loader = Dialog(this).apply {
            setContentView(R.layout.item_load)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setCancelable(false)
        }
    }
    fun handleLoader(array: ArrayList<Any>, swipeRefreshLayout: SwipeRefreshLayout? = null, successResponse: (Resource<Any>) -> Unit) {
        swipeRefreshLayout?.isRefreshing = false
        if(array.isEmpty()){
            showLoader()

        }else{
            hideLoader()

        }


    /*    when (showLoader) {

            is showLoader -> {
                Timber.v("okhttp: State Loading")
                if (showLoader)
                    showLoader()
            }

            is showLoader-> {
//                FileReadWriteUtil(this).writeFileOnInternalStorage("API_Response.txt", GeneralFunctions.prettifyJson(Gson().toJson(resource.data))!!)
                Timber.v("okhttp: State Success")
                hideLoader()
            }

            else -> {}
        }*/
    }
    fun showLoader() {
        if (!loader.isShowing)
            loader.show()
    }

    fun hideLoader() {
        if (loader.isShowing)
            loader.dismiss()
    }
}