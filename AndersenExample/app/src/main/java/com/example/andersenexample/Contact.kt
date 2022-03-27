package com.example.andersenexample

import java.io.Serializable

data class Contact(
    val id: Int,
    val name: String,
    val phone: String
) : Serializable