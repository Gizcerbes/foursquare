package com.uogames.innowisefoursquare.network.v3.response


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("context")
    val context: Context,
    @SerializedName("results")
    val results: List<Result>
) {

    data class Icon(
        @SerializedName("prefix")
        val prefix: String,
        @SerializedName("suffix")
        val suffix: String
    )

    data class Category(
        @SerializedName("icon")
        val icon: Icon,
        @SerializedName("id")
        val id: Int,
        @SerializedName("name")
        val name: String,
        @SerializedName("plural_name")
        val pluralName: String,
        @SerializedName("short_name")
        val shortName: String
    )

    data class Parent(
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("fsq_id")
        val fsqId: String,
        @SerializedName("name")
        val name: String
    )

    data class Children(
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("fsq_id")
        val fsqId: String,
        @SerializedName("name")
        val name: String
    )

    data class RelatedPlaces(
        @SerializedName("children")
        val children: List<Children>,
        @SerializedName("parent")
        val parent: Parent
    )

    data class Location(
        @SerializedName("address")
        val address: String,
        @SerializedName("country")
        val country: String,
        @SerializedName("cross_street")
        val crossStreet: String,
        @SerializedName("formatted_address")
        val formattedAddress: String,
        @SerializedName("locality")
        val locality: String,
        @SerializedName("postcode")
        val postcode: String,
        @SerializedName("region")
        val region: String
    )

    data class Context(
        @SerializedName("geo_bounds")
        val geoBounds: GeoBounds
    ) {
        data class GeoBounds(
            @SerializedName("circle")
            val circle: Circle
        ) {
            data class Circle(
                @SerializedName("center")
                val center: Center,
                @SerializedName("radius")
                val radius: Int
            ) {
                data class Center(
                    @SerializedName("latitude")
                    val latitude: Double,
                    @SerializedName("longitude")
                    val longitude: Double
                )
            }
        }
    }

    data class Geocodes(
        @SerializedName("main")
        val main: Main
    ) {
        data class Main(
            @SerializedName("latitude")
            val latitude: Double,
            @SerializedName("longitude")
            val longitude: Double
        )
    }

    data class Result(
        @SerializedName("categories")
        val categories: List<Category>,
        @SerializedName("chains")
        val chains: List<Any>,
        @SerializedName("closed_bucket")
        val closedBucket: String,
        @SerializedName("distance")
        val distance: Int,
        @SerializedName("fsq_id")
        val fsqId: String,
        @SerializedName("geocodes")
        val geocodes: Geocodes,
        @SerializedName("link")
        val link: String,
        @SerializedName("location")
        val location: Location,
        @SerializedName("name")
        val name: String,
        @SerializedName("related_places")
        val relatedPlaces: RelatedPlaces,
        @SerializedName("timezone")
        val timezone: String
    )

}