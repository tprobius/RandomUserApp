package com.tprobius.randomuserapp.data.model

import com.google.gson.annotations.SerializedName
import com.tprobius.randomuserapp.domain.entities.LoginDmn

data class LoginDt(
    @SerializedName("uuid")
    val uuid: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("salt")
    val salt: String?,
    @SerializedName("md5")
    val md5: String?,
    @SerializedName("sha1")
    val sha1: String?,
    @SerializedName("sha256")
    val sha256: String?
) {

    fun toLoginDmn() = LoginDmn(
        uuid = uuid,
        username = username,
        password = password,
        salt = salt,
        md5 = md5,
        sha1 = sha1,
        sha256 = sha256
    )
}