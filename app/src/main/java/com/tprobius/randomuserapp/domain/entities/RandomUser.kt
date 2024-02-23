package com.tprobius.randomuserapp.domain.entities

data class RandomUser(
    val gender: String?,
    val nameDmn: NameDmn?,
    val locationDmn: LocationDmn?,
    val email: String?,
    val loginDmn: LoginDmn?,
    val dobDmn: DobDmn?,
    val registeredDmn: RegisteredDmn?,
    val phone: String?,
    val cell: String?,
    val idDmn: IdDmn?,
    val pictureDmn: PictureDmn?,
    val nat: String?
)