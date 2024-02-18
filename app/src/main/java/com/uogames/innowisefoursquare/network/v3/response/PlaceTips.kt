package com.uogames.innowisefoursquare.network.v3.response


import com.google.gson.annotations.SerializedName

class PlaceTips : ArrayList<PlaceTips.PlaceTipsItem>(){
    data class PlaceTipsItem(
        @SerializedName("created_at")
        val createdAt: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("text")
        val text: String
    )
}