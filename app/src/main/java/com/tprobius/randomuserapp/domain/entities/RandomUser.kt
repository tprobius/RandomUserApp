package com.tprobius.randomuserapp.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "users")
data class RandomUser(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val gender: String?,
    val title: String?,
    val first: String?,
    val last: String?,
    val number: Int?,
    val street: String?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val latitude: String?,
    val longitude: String?,
    val offset: String?,
    val timezone: String?,
    val email: String?,
    val date: String?,
    val age: Int?,
    val phone: String?,
    val cell: String?,
    val large: String?,
    val medium: String?,
    val thumbnail: String?,
    val nat: String?
) : Parcelable