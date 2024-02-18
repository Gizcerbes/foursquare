package com.uogames.innowisefoursquare.network.v3

import com.uogames.innowisefoursquare.network.v3.response.PlaceDetailResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import javax.inject.Named

class NetworkPlaceDetailProvider @Inject constructor(
	@param:Named("place_api") private val client: HttpClient
) {


	suspend fun getPlaceDetail(
		fsqId: String,
		fields: String? = null,
		sessionToken: String? = null
	) = client.get("https://api.foursquare.com/v3/places/$fsqId") {
		fields?.run { parameter("fields", this) }
		sessionToken?.run { parameter("session_token", this) }
	}.body<PlaceDetailResponse>()


}