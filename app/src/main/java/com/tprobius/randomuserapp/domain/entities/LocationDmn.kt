package com.tprobius.randomuserapp.domain.entities

data class LocationDmn(
    val streetDmn: StreetDmn?,
    val city: String?,
    val state: String?,
    val country: String?,
    val postcode: String?,
    val timezoneDmn: TimezoneDmn?
)