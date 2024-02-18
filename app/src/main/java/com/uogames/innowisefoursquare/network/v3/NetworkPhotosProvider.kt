package com.uogames.innowisefoursquare.network.v3

import com.uogames.innowisefoursquare.network.v3.response.PlacePhotoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import javax.inject.Named

class NetworkPhotosProvider @Inject constructor(
	@param:Named("place_api") private val client: HttpClient
) {

	companion object{

		const val SORT_POPULAR = "popular"
		const val SORT_NEWEST = "newest"

		const val CLASSIFICATION_FOOD = "food"
		const val CLASSIFICATION_INDOOR = "indoor"
		const val CLASSIFICATION_MENU = "menu"
		const val CLASSIFICATION_OUTDOOR = "outdoor"

	}

	suspend fun getPlacePhotos(
		fsqId: String,
		limit: Int? = null,
		sort: String? = null,
		classifications: String? = null
	) = client.get("https://api.foursquare.com/v3/places/$fsqId/photos") {
		limit?.also { parameter("limit", it) }
		sort?.also { parameter("sort", it) }
		classifications?.also { parameter("classifications", it) }
	}.body<PlacePhotoResponse>()


}