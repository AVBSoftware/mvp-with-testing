package com.example.photogalearykotlin.model

import com.google.gson.annotations.SerializedName

class UserAccount {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("username")
    var username: String? = null

    @SerializedName("name")
    var name: String? = null
}