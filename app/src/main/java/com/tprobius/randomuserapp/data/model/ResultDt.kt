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
        nameDmn = nameDt?.toNameDmn(),
        locationDmn = locationDt?.toLocationDmn(),
        email = email,
        loginDmn = loginDt?.toLoginDmn(),
        dobDmn = dobDt?.toDobDmn(),
        registeredDmn = registeredDt?.toRegisteredDmn(),
        phone = phone,
        cell = cell,
        idDmn = idDt?.toIdDmn(),
        pictureDmn = pictureDt?.toPictureDmn(),
        nat = nat
    )
}