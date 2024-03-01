package com.tprobius.randomuserapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RandomUser(
    val id: Long = 0,
    val gender: String? = null,
    val title: String? = null,
    val first: String? = null,
    val last: String? = null,
    val number: Int? = null,
    val street: String? = null,
    val city: String? = null,
    val state: String? = null,
    val country: String? = null,
    val postcode: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    val offset: String? = null,
    val timezone: String? = null,
    val email: String? = null,
    val date: String? = null,
    val age: Int? = null,
    val phone: String? = null,
    val cell: String? = null,
    val large: String? = null,
    val medium: String? = null,
    val thumbnail: String? = null,
    val nat: String? = null
) : Parcelable