package com.example.contacts

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    val id: Int,
    var name: String,
    var surname: String,
    var phone: Long,
    var imageUri: String,
) : Parcelable