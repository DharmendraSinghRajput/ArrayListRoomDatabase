package com.example.roomdatabasearray.utils
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.showToast(message: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()

fun Fragment.showToast(message: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this.requireContext(), message, length).show()

fun Context.showToast(message: String, length: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, message, length).show()
