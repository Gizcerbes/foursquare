package com.uogames.innowisefoursquare.network.v2.response


import com.google.gson.annotations.SerializedName

data class UserSelfResponse(
    @SerializedName("meta")
    val meta: Meta?,
    @SerializedName("notifications")
    val notifications: List<Notification?>?,
    @SerializedName("response")
    val response: Response?
) {
    data class Meta(
        @SerializedName("code")
        val code: Number?,
        @SerializedName("requestId")
        val requestId: String?
    )

    data class Notification(
        @SerializedName("item")
        val item: Item?,
        @SerializedName("type")
        val type: String?
    ) {
        data class Item(
            @SerializedName("unreadCount")
            val unreadCount: Number?
        )
    }

    data class Response(
        @SerializedName("user")
        val user: User?
    ) {
        data class User(
            @SerializedName("bio")
            val bio: String?,
            @SerializedName("blockedStatus")
            val blockedStatus: String?,
            @SerializedName("canonicalUrl")
            val canonicalUrl: String?,
            @SerializedName("checkinPings")
            val checkinPings: String?,
            @SerializedName("checkins")
            val checkins: Checkins?,
            @SerializedName("contact")
            val contact: Contact?,
            @SerializedName("countryCode")
            val countryCode: String?,
            @SerializedName("createdAt")
            val createdAt: Number?,
            @SerializedName("firstName")
            val firstName: String?,
            @SerializedName("followers")
            val followers: Followers?,
            @SerializedName("following")
            val following: Following?,
            @SerializedName("friends")
            val friends: Friends?,
            @SerializedName("gender")
            val gender: String?,
            @SerializedName("handle")
            val handle: String?,
            @SerializedName("homeCity")
            val homeCity: String?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("lastName")
            val lastName: String?,
            @SerializedName("lenses")
            val lenses: List<Any?>?,
            @SerializedName("lists")
            val lists: Lists?,
            @SerializedName("mayorships")
            val mayorships: Mayorships?,
            @SerializedName("photo")
            val photo: Photo?,
            @SerializedName("photos")
            val photos: Photos?,
            @SerializedName("pings")
            val pings: Boolean?,
            @SerializedName("privateProfile")
            val privateProfile: Boolean?,
            @SerializedName("referralId")
            val referralId: String?,
            @SerializedName("relationship")
            val relationship: String?,
            @SerializedName("requests")
            val requests: Requests?,
            @SerializedName("tips")
            val tips: Tips?,
            @SerializedName("type")
            val type: String?
        ) {
            data class Checkins(
                @SerializedName("count")
                val count: Number?,
                @SerializedName("items")
                val items: List<Any?>?
            )

            data class Contact(
                @SerializedName("email")
                val email: String?,
                @SerializedName("verifiedEmail")
                val verifiedEmail: String?,
                @SerializedName("verifiedPhone")
                val verifiedPhone: String?
            )

            data class Followers(
                @SerializedName("count")
                val count: Number?,
                @SerializedName("groups")
                val groups: List<Any?>?
            )

            data class Following(
                @SerializedName("count")
                val count: Number?
            )

            data class Friends(
                @SerializedName("count")
                val count: Number?,
                @SerializedName("groups")
                val groups: List<Group?>?
            ) {
                data class Group(
                    @SerializedName("count")
                    val count: Number?,
                    @SerializedName("items")
                    val items: List<Any?>?,
                    @SerializedName("name")
                    val name: String?,
                    @SerializedName("type")
                    val type: String?
                )
            }

            data class Lists(
                @SerializedName("count")
                val count: Number?,
                @SerializedName("groups")
                val groups: List<Group?>?
            ) {
                data class Group(
                    @SerializedName("count")
                    val count: Number?,
                    @SerializedName("items")
                    val items: List<Item?>?,
                    @SerializedName("type")
                    val type: String?
                ) {
                    data class Item(
                        @SerializedName("canonicalUrl")
                        val canonicalUrl: String?,
                        @SerializedName("collaborative")
                        val collaborative: Boolean?,
                        @SerializedName("description")
                        val description: String?,
                        @SerializedName("editable")
                        val editable: Boolean?,
                        @SerializedName("id")
                        val id: String?,
                        @SerializedName("listItems")
                        val listItems: ListItems?,
                        @SerializedName("name")
                        val name: String?,
                        @SerializedName("public")
                        val `public`: Boolean?,
                        @SerializedName("type")
                        val type: String?,
                        @SerializedName("url")
                        val url: String?
                    ) {
                        data class ListItems(
                            @SerializedName("count")
                            val count: Number?
                        )
                    }
                }
            }

            data class Mayorships(
                @SerializedName("count")
                val count: Number?,
                @SerializedName("items")
                val items: List<Any?>?
            )

            data class Photo(
                @SerializedName("default")
                val default: Boolean?,
                @SerializedName("prefix")
                val prefix: String?,
                @SerializedName("suffix")
                val suffix: String?
            )

            data class Photos(
                @SerializedName("count")
                val count: Number?,
                @SerializedName("items")
                val items: List<Any?>?
            )

            data class Requests(
                @SerializedName("count")
                val count: Number?
            )

            data class Tips(
                @SerializedName("count")
                val count: Number?
            )
        }
    }
}