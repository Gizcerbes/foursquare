package com.uogames.innowisefoursquare.network.v3.response


import com.google.gson.annotations.SerializedName

data class PlaceDetailResponse(
    @SerializedName("categories")
    val categories: List<Category?>?,
    @SerializedName("chains")
    val chains: List<Chain?>?,
    @SerializedName("closed_bucket")
    val closedBucket: String?,
    @SerializedName("date_closed")
    val dateClosed: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("distance")
    val distance: Number?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("fax")
    val fax: String?,
    @SerializedName("features")
    val features: Features?,
    @SerializedName("fsq_id")
    val fsqId: String?,
    @SerializedName("geocodes")
    val geocodes: Geocodes?,
    @SerializedName("hours")
    val hours: Hours?,
    @SerializedName("hours_popular")
    val hoursPopular: List<HoursPopular?>?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("location")
    val location: Location?,
    @SerializedName("menu")
    val menu: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("photos")
    val photos: List<Photo?>?,
    @SerializedName("popularity")
    val popularity: Number?,
    @SerializedName("price")
    val price: Number?,
    @SerializedName("rating")
    val rating: Number?,
    @SerializedName("related_places")
    val relatedPlaces: RelatedPlaces?,
    @SerializedName("social_media")
    val socialMedia: SocialMedia?,
    @SerializedName("stats")
    val stats: Stats?,
    @SerializedName("store_id")
    val storeId: String?,
    @SerializedName("tastes")
    val tastes: List<String?>?,
    @SerializedName("tel")
    val tel: String?,
    @SerializedName("timezone")
    val timezone: String?,
    @SerializedName("tips")
    val tips: List<Tip?>?,
    @SerializedName("venue_reality_bucket")
    val venueRealityBucket: String?,
    @SerializedName("verified")
    val verified: Boolean?,
    @SerializedName("website")
    val website: String?
) {
    data class Category(
        @SerializedName("icon")
        val icon: Icon?,
        @SerializedName("id")
        val id: Number?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("plural_name")
        val pluralName: String?,
        @SerializedName("short_name")
        val shortName: String?
    ) {
        data class Icon(
            @SerializedName("classifications")
            val classifications: List<String?>?,
            @SerializedName("created_at")
            val createdAt: String?,
            @SerializedName("height")
            val height: Number?,
            @SerializedName("id")
            val id: String?,
            @SerializedName("prefix")
            val prefix: String?,
            @SerializedName("suffix")
            val suffix: String?,
            @SerializedName("tip")
            val tip: Tip?,
            @SerializedName("width")
            val width: Number?
        ) {
            data class Tip(
                @SerializedName("agree_count")
                val agreeCount: Number?,
                @SerializedName("created_at")
                val createdAt: String?,
                @SerializedName("disagree_count")
                val disagreeCount: Number?,
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

    data class Chain(
        @SerializedName("id")
        val id: String?,
        @SerializedName("name")
        val name: String?
    )

    data class Features(
        @SerializedName("amenities")
        val amenities: Amenities?,
        @SerializedName("attributes")
        val attributes: Attributes?,
        @SerializedName("food_and_drink")
        val foodAndDrink: FoodAndDrink?,
        @SerializedName("payment")
        val payment: Payment?,
        @SerializedName("services")
        val services: Services?
    ) {
        data class Amenities(
            @SerializedName("atm")
            val atm: Atm?,
            @SerializedName("coat_check")
            val coatCheck: CoatCheck?,
            @SerializedName("jukebox")
            val jukebox: Jukebox?,
            @SerializedName("live_music")
            val liveMusic: LiveMusic?,
            @SerializedName("music")
            val music: Music?,
            @SerializedName("outdoor_seating")
            val outdoorSeating: OutdoorSeating?,
            @SerializedName("parking")
            val parking: Parking?,
            @SerializedName("private_room")
            val privateRoom: PrivateRoom?,
            @SerializedName("restroom")
            val restroom: Restroom?,
            @SerializedName("sit_down_dining")
            val sitDownDining: SitDownDining?,
            @SerializedName("smoking")
            val smoking: Smoking?,
            @SerializedName("tvs")
            val tvs: Tvs?,
            @SerializedName("wheelchair_accessible")
            val wheelchairAccessible: WheelchairAccessible?,
            @SerializedName("wifi")
            val wifi: String?
        ) {
            class Atm

            class CoatCheck

            class Jukebox

            class LiveMusic

            class Music

            class OutdoorSeating

            data class Parking(
                @SerializedName("parking")
                val parking: Parking?,
                @SerializedName("private_lot")
                val privateLot: PrivateLot?,
                @SerializedName("public_lot")
                val publicLot: PublicLot?,
                @SerializedName("street_parking")
                val streetParking: StreetParking?,
                @SerializedName("valet_parking")
                val valetParking: ValetParking?
            ) {
                class Parking

                class PrivateLot

                class PublicLot

                class StreetParking

                class ValetParking
            }

            class PrivateRoom

            class Restroom

            class SitDownDining

            class Smoking

            class Tvs

            class WheelchairAccessible
        }

        data class Attributes(
            @SerializedName("business_meeting")
            val businessMeeting: String?,
            @SerializedName("clean")
            val clean: String?,
            @SerializedName("crowded")
            val crowded: String?,
            @SerializedName("dates_popular")
            val datesPopular: String?,
            @SerializedName("dressy")
            val dressy: String?,
            @SerializedName("families_popular")
            val familiesPopular: String?,
            @SerializedName("gluten_free_diet")
            val glutenFreeDiet: String?,
            @SerializedName("good_for_dogs")
            val goodForDogs: String?,
            @SerializedName("groups_popular")
            val groupsPopular: String?,
            @SerializedName("healthy_diet")
            val healthyDiet: String?,
            @SerializedName("late_night")
            val lateNight: String?,
            @SerializedName("noisy")
            val noisy: String?,
            @SerializedName("quick_bite")
            val quickBite: String?,
            @SerializedName("romantic")
            val romantic: String?,
            @SerializedName("service_quality")
            val serviceQuality: String?,
            @SerializedName("singles_popular")
            val singlesPopular: String?,
            @SerializedName("special_occasion")
            val specialOccasion: String?,
            @SerializedName("trendy")
            val trendy: String?,
            @SerializedName("value_for_money")
            val valueForMoney: String?,
            @SerializedName("vegan_diet")
            val veganDiet: String?,
            @SerializedName("vegetarian_diet")
            val vegetarianDiet: String?
        )

        data class FoodAndDrink(
            @SerializedName("alcohol")
            val alcohol: Alcohol?,
            @SerializedName("meals")
            val meals: Meals?
        ) {
            data class Alcohol(
                @SerializedName("bar_service")
                val barService: BarService?,
                @SerializedName("beer")
                val beer: Beer?,
                @SerializedName("byo")
                val byo: Byo?,
                @SerializedName("cocktails")
                val cocktails: Cocktails?,
                @SerializedName("full_bar")
                val fullBar: FullBar?,
                @SerializedName("wine")
                val wine: Wine?
            ) {
                class BarService

                class Beer

                class Byo

                class Cocktails

                class FullBar

                class Wine
            }

            data class Meals(
                @SerializedName("bar_snacks")
                val barSnacks: BarSnacks?,
                @SerializedName("breakfast")
                val breakfast: Breakfast?,
                @SerializedName("brunch")
                val brunch: Brunch?,
                @SerializedName("dessert")
                val dessert: Dessert?,
                @SerializedName("dinner")
                val dinner: Dinner?,
                @SerializedName("happy_hour")
                val happyHour: HappyHour?,
                @SerializedName("lunch")
                val lunch: Lunch?,
                @SerializedName("tasting_menu")
                val tastingMenu: TastingMenu?
            ) {
                class BarSnacks

                class Breakfast

                class Brunch

                class Dessert

                class Dinner

                class HappyHour

                class Lunch

                class TastingMenu
            }
        }

        data class Payment(
            @SerializedName("credit_cards")
            val creditCards: CreditCards?,
            @SerializedName("digital_wallet")
            val digitalWallet: DigitalWallet?
        ) {
            data class CreditCards(
                @SerializedName("accepts_credit_cards")
                val acceptsCreditCards: AcceptsCreditCards?,
                @SerializedName("amex")
                val amex: Amex?,
                @SerializedName("diners_club")
                val dinersClub: DinersClub?,
                @SerializedName("discover")
                val discover: Discover?,
                @SerializedName("master_card")
                val masterCard: MasterCard?,
                @SerializedName("union_pay")
                val unionPay: UnionPay?,
                @SerializedName("visa")
                val visa: Visa?
            ) {
                class AcceptsCreditCards

                class Amex

                class DinersClub

                class Discover

                class MasterCard

                class UnionPay

                class Visa
            }

            data class DigitalWallet(
                @SerializedName("accepts_nfc")
                val acceptsNfc: AcceptsNfc?
            ) {
                class AcceptsNfc
            }
        }

        data class Services(
            @SerializedName("delivery")
            val delivery: Delivery?,
            @SerializedName("dine_in")
            val dineIn: DineIn?,
            @SerializedName("drive_through")
            val driveThrough: DriveThrough?,
            @SerializedName("takeout")
            val takeout: Takeout?
        ) {
            class Delivery

            data class DineIn(
                @SerializedName("essential_reservations")
                val essentialReservations: EssentialReservations?,
                @SerializedName("groups_only_reservations")
                val groupsOnlyReservations: GroupsOnlyReservations?,
                @SerializedName("online_reservations")
                val onlineReservations: OnlineReservations?,
                @SerializedName("reservations")
                val reservations: Reservations?
            ) {
                class EssentialReservations

                class GroupsOnlyReservations

                class OnlineReservations

                class Reservations
            }

            class DriveThrough

            class Takeout
        }
    }

    data class Geocodes(
        @SerializedName("drop_off")
        val dropOff: DropOff?,
        @SerializedName("front_door")
        val frontDoor: FrontDoor?,
        @SerializedName("main")
        val main: Main?,
        @SerializedName("road")
        val road: Road?,
        @SerializedName("roof")
        val roof: Roof?
    ) {
        data class DropOff(
            @SerializedName("latitude")
            val latitude: Number?,
            @SerializedName("longitude")
            val longitude: Number?
        )

        data class FrontDoor(
            @SerializedName("latitude")
            val latitude: Number?,
            @SerializedName("longitude")
            val longitude: Number?
        )

        data class Main(
            @SerializedName("latitude")
            val latitude: Number?,
            @SerializedName("longitude")
            val longitude: Number?
        )

        data class Road(
            @SerializedName("latitude")
            val latitude: Number?,
            @SerializedName("longitude")
            val longitude: Number?
        )

        data class Roof(
            @SerializedName("latitude")
            val latitude: Number?,
            @SerializedName("longitude")
            val longitude: Number?
        )
    }

    data class Hours(
        @SerializedName("display")
        val display: String?,
        @SerializedName("is_local_holiday")
        val isLocalHoliday: Boolean?,
        @SerializedName("open_now")
        val openNow: Boolean?,
        @SerializedName("regular")
        val regular: List<Regular?>?
    ) {
        data class Regular(
            @SerializedName("close")
            val close: String?,
            @SerializedName("day")
            val day: Number?,
            @SerializedName("open")
            val `open`: String?
        )
    }

    data class HoursPopular(
        @SerializedName("close")
        val close: String?,
        @SerializedName("day")
        val day: Number?,
        @SerializedName("open")
        val `open`: String?
    )

    data class Location(
        @SerializedName("address")
        val address: String?,
        @SerializedName("address_extended")
        val addressExtended: String?,
        @SerializedName("admin_region")
        val adminRegion: String?,
        @SerializedName("census_block")
        val censusBlock: String?,
        @SerializedName("country")
        val country: String?,
        @SerializedName("cross_street")
        val crossStreet: String?,
        @SerializedName("dma")
        val dma: String?,
        @SerializedName("formatted_address")
        val formattedAddress: String?,
        @SerializedName("locality")
        val locality: String?,
        @SerializedName("neighborhood")
        val neighborhood: List<String?>?,
        @SerializedName("po_box")
        val poBox: String?,
        @SerializedName("post_town")
        val postTown: String?,
        @SerializedName("postcode")
        val postcode: String?,
        @SerializedName("region")
        val region: String?
    )

    data class Photo(
        @SerializedName("classifications")
        val classifications: List<String?>?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("height")
        val height: Number?,
        @SerializedName("id")
        val id: String?,
        @SerializedName("prefix")
        val prefix: String?,
        @SerializedName("suffix")
        val suffix: String?,
        @SerializedName("tip")
        val tip: Tip?,
        @SerializedName("width")
        val width: Number?
    ) {
        data class Tip(
            @SerializedName("agree_count")
            val agreeCount: Number?,
            @SerializedName("created_at")
            val createdAt: String?,
            @SerializedName("disagree_count")
            val disagreeCount: Number?,
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

    class RelatedPlaces

    data class SocialMedia(
        @SerializedName("facebook_id")
        val facebookId: String?,
        @SerializedName("instagram")
        val instagram: String?,
        @SerializedName("twitter")
        val twitter: String?
    )

    data class Stats(
        @SerializedName("total_photos")
        val totalPhotos: Number?,
        @SerializedName("total_ratings")
        val totalRatings: Number?,
        @SerializedName("total_tips")
        val totalTips: Number?
    )

    data class Tip(
        @SerializedName("agree_count")
        val agreeCount: Number?,
        @SerializedName("created_at")
        val createdAt: String?,
        @SerializedName("disagree_count")
        val disagreeCount: Number?,
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