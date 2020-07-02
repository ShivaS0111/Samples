package com.example.myapplication.ui

import android.app.Activity
import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.showToast(string: String) {
    Toast.makeText(requireContext(), string, Toast.LENGTH_SHORT).show()
}

fun Activity.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}
