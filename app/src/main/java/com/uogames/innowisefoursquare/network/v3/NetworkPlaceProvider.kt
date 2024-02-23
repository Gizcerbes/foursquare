package com.uogames.innowisefoursquare.network.v3

import com.uogames.innowisefoursquare.network.v3.response.SearchResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject
import javax.inject.Named

class NetworkPlaceProvider @Inject constructor(
	@param:Named("place_api") private val client: HttpClient
) {

	companion object {

		const val SORT_RELEVANCE = "relevance"
		const val SORT_RATING = "rating"
		const val SORT_DISTANCE = "distance"
	}

	suspend fun placesSearch(
		query: String? = null,
		latitude: String? = null,
		longitude: String? = null,
		radius: Int? = null,
		categories: String? = null,
		chains: String? = null,
		excludeChains: String? = null,
		excludeAllChains: Boolean? = null,
		fields: String? = null,
		minPrice: Int? = null,
		maxPrice: Int? = null,
		openAt: String? = null,
		openNow: Boolean? = null,
		ne: String? = null,
		sw: String? = null,
		near: String? = null,
		polygon: String? = null,
		sort: String? = null,
		limit: Int? = null,
		sessionToken: String? = null,
		superVenueID: String? = null
	) = client.get("search") {
		query?.run { parameter("query", this) }
		if (latitude != null && longitude != null) parameter("ll", "$latitude,$longitude")
		radius?.run { parameter("radius", this) }
		categories?.run { parameter("categories", this) }
		chains?.run { parameter("chains", this) }
		excludeChains?.run { parameter("exclude_chains", this) }
		excludeAllChains?.run { parameter("exclude_all_chains", this) }
		fields?.run { parameter("fields", this) }
		minPrice?.run { parameter("min_price", this) }
		maxPrice?.run { parameter("max_price", this) }
		openAt?.run { parameter("open_at", this) }
		openNow?.run { parameter("open_now", this) }
		ne?.run { parameter("ne", this) }
		sw?.run { parameter("sw", this) }
		near?.run { parameter("near", this) }
		polygon?.run { parameter("polygon", this) }
		sort?.run { parameter("sort", this) }
		limit?.run { parameter("limit", this) }
		sessionToken?.run { parameter("session_token", this) }
		superVenueID?.run { parameter("super_venue_id", this) }
	}.body<SearchResponse>()

}