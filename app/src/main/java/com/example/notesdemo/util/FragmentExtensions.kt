package com.example.notesdemo.util

import androidx.fragment.app.Fragment
import com.example.notesdemo.MainActivity

fun Fragment.setActionBarTitle(title: String){
    if (requireActivity() is MainActivity) {
        (requireActivity() as MainActivity).supportActionBar?.title = title
    }
}