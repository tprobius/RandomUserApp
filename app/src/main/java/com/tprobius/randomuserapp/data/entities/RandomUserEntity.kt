package com.tprobius.randomuserapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tprobius.randomuserapp.domain.model.RandomUser

@Entity(tableName = "users")
data class RandomUserEntity(
    @PrimaryKey(autoGenerate = true)
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
) {
    fun toRandomUser() = RandomUser(
        gender = gender,
        title = title,
        first = first,
        last = last,
        number = number,
        street = street,
        city = city,
        state = state,
        country = country,
        postcode = postcode,
        latitude = latitude,
        longitude = longitude,
        offset = offset,
        timezone = timezone,
        email = email,
        date = date,
        age = age,
        phone = phone,
        cell = cell,
        large = large,
        medium = medium,
        thumbnail = thumbnail,
        nat = nat
    )

    companion object {
        fun toRandomUserEntity(randomUser: RandomUser) = RandomUserEntity(
            gender = randomUser.gender,
            title = randomUser.title,
            first = randomUser.first,
            last = randomUser.last,
            number = randomUser.number,
            street = randomUser.street,
            city = randomUser.city,
            state = randomUser.state,
            country = randomUser.country,
            postcode = randomUser.postcode,
            latitude = randomUser.latitude,
            longitude = randomUser.longitude,
            offset = randomUser.offset,
            timezone = randomUser.timezone,
            email = randomUser.email,
            date = randomUser.date,
            age = randomUser.age,
            phone = randomUser.phone,
            cell = randomUser.cell,
            large = randomUser.large,
            medium = randomUser.medium,
            thumbnail = randomUser.thumbnail,
            nat = randomUser.nat
        )
    }
}