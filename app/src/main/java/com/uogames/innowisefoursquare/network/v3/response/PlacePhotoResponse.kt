package com.uogames.innowisefoursquare.network.v3.response


import com.google.gson.annotations.SerializedName

class PlacePhotoResponse : ArrayList<PlacePhotoResponse.PlacePhotoItem>(){
    data class PlacePhotoItem(
        @SerializedName("classifications")
        val classifications: List<String?>?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("height")
        val height: Int?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("prefix")
        val prefix: String?,
        @SerializedName("suffix")
        val suffix: String?,
        @SerializedName("tip")
        val tip: Tip?,
        @SerializedName("width")
        val width: Int?
    ) {
        data class Tip(
            @SerializedName("agree_count")
            val agreeCount: Int?,
            @SerializedName("created_at")
            val createdAt: String?,
            @SerializedName("disagree_count")
            val disagreeCount: Int?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("lang")
            val lang: String?,
            @SerializedName("text")
            val text: String?,
            @SerializedName("url")
            val url: String?
        )
    }
}