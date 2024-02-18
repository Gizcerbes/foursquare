package com.uogames.innowisefoursquare.network.v3.response


import com.google.gson.annotations.SerializedName

data class AccessTokenResponse(
    @SerializedName("access_token")
    val accessToken: String
)