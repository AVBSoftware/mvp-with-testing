package com.example.photogalearykotlin.model

import com.google.gson.annotations.SerializedName

class album {
    @SerializedName("description")
    var description: String? = null

    @SerializedName("alt_description")
    var alt_description: String? = null

    @SerializedName("user")
    var user: UserAccount? = null

    @SerializedName("urls")
    var urls: Urlimage? = null

}