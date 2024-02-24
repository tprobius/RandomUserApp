package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.RandomUser

data class ResultDt(
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("name")
    val nameDt: NameDt?,
    @SerializedName("location")
    val locationDt: LocationDt?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("login")
    val loginDt: LoginDt?,
    @SerializedName("dob")
    val dobDt: DobDt?,
    @SerializedName("registered")
    val registeredDt: RegisteredDt?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("cell")
    val cell: String?,
    @SerializedName("id")
    val idDt: IdDt?,
    @SerializedName("picture")
    val pictureDt: PictureDt?,
    @SerializedName("nat")
    val nat: String?
) {

    fun toRandomUser() = RandomUser(
        gender = gender,
        title = nameDt?.title,
        first = nameDt?.first,
        last = nameDt?.last,
        number = locationDt?.streetDt?.number,
        street = locationDt?.streetDt?.name,
        city = locationDt?.city,
        state = locationDt?.state,
        country = locationDt?.country,
        postcode = locationDt?.postcode,
        latitude = locationDt?.coordinates?.latitude,
        longitude = locationDt?.coordinates?.longitude,
        offset = locationDt?.timezoneDt?.offset,
        timezone = locationDt?.timezoneDt?.description,
        email = email,
        date = dobDt?.date,
        age = dobDt?.age,
        phone = phone,
        cell = cell,
        large = pictureDt?.large,
        medium = pictureDt?.medium,
        thumbnail = pictureDt?.thumbnail,
        nat = nat
    )
}