package com.example.roomdatabasearray.ui.activity

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.viewModels
import com.example.roomdatabasearray.R
import com.example.roomdatabasearray.databinding.ActivityRegisterBinding
import com.example.roomdatabasearray.ui.models.RegisterTableResponse
import com.example.roomdatabasearray.ui.viewmodel.PostViewModel
import com.example.roomdatabasearray.utils.BaseActivity
import com.example.roomdatabasearray.utils.GeneralFunctions
import com.example.roomdatabasearray.utils.showToast
import com.github.dhaval2404.imagepicker.ImagePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity : BaseActivity() {

    private val mBinding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }
    val mainViewModel: PostViewModel by viewModels()
    var uri: Uri? = null
    var image: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.apply {
            //mainViewModel.registerUser()
            imgProfile?.setOnClickListener {
                ImagePicker.with(this@RegisterActivity)
                    .compress(1024)
                    .maxResultSize(1080, 1080)
                    .start()
            }
           buttonSave.setOnClickListener {
               val name = editUserName.text.toString().trim()
               val contact = editContact.text.toString().trim()
               val email = editEmail.text.toString().trim()
               val password = editPassword.text.toString().trim()
               if (email.isNotEmpty() && contact.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {

                   CoroutineScope(Dispatchers.Main).launch {
                       val registerTableResponse = RegisterTableResponse(
                           0,
                           name = name,
                           contact_number = contact,
                           email = email,
                           password = password,
                           image = image!!)

                       var resultData=mainViewModel.registerUser(registerTableResponse)
                       startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                       showToast("Register user")
                       Log.d("ResultDataResponse","${resultData}")
                   }
               }else{
                   showToast("Please fill")
               }
           }

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            uri = data?.data!!
            image = MediaStore.Images.Media.getBitmap(this.contentResolver, this.uri) as Bitmap?
            mBinding?.imgProfile?.setImageBitmap(image)
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            showToast(ImagePicker.getError(data))
        } else {
            showToast("Task Cancelled")
        }
    }

    fun validation():Boolean {
        mBinding.apply {
            if(editUserName.text.toString().isEmpty())
                return GeneralFunctions.showTextInputLayoutError(tnlUserName,getString(R.string.please_enter_the_name))
            else
                GeneralFunctions.hideTextInputLayoutError(tnlUserName)

            if(editContact.text.toString().isEmpty())
                return GeneralFunctions.showTextInputLayoutError(tnlUserName,getString(R.string.please_enter_the_phone))
            else
                GeneralFunctions.hideTextInputLayoutError(tnlUserName)

            if (editEmail.text.toString().isEmpty())
                return GeneralFunctions.validateEmail(this@RegisterActivity,tnlUserEmail,editEmail)
            else
                GeneralFunctions.hideTextInputLayoutError(tnlUserEmail)

            if(editPassword.text.toString().isEmpty())
                return  GeneralFunctions.validatePassword(this@RegisterActivity,tnlUserPassword,editPassword)
            else
                GeneralFunctions.hideTextInputLayoutError(tnlUserPassword)

            return true
        }
    }

}