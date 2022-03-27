package com.example.andersenexample

import androidx.fragment.app.Fragment

interface Navigator {

    fun navigate(fragment: Fragment)
    fun goBack()

}