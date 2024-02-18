package com.uogames.innowisefoursquare.provider

import android.util.Log
import com.uogames.innowisefoursquare.database.dao.PlaceDAO
import com.uogames.innowisefoursquare.network.v3.NetworkPlaceProvider
import com.uogames.innowisefoursquare.provider.dto.PlaceDTO
import com.uogames.innowisefoursquare.provider.map.PlaceMap.toDTO
import com.uogames.innowisefoursquare.provider.map.PlaceMap.toEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class PlaceProvider @Inject constructor(
	private val provider: NetworkPlaceProvider,
	private val dao: PlaceDAO
) {


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
	): List<PlaceDTO> {
		return try {
			val r = provider.placesSearch(
				query,
				latitude,
				longitude,
				radius,
				categories,
				chains,
				excludeChains,
				excludeAllChains,
				fields,
				minPrice,
				maxPrice,
				openAt,
				openNow,
				ne,
				sw,
				near,
				polygon,
				sort,
				limit,
				sessionToken,
				superVenueID
			)
			withContext(coroutineContext) { dao.insert(r.results.map { it.toEntity() }) }
			r.results.map { it.toDTO() }
		} catch (e: Throwable) {
			Log.e("TAG", "placesSearch: $e", )
			dao.getList().map { it.toDTO() }
		}
	}

	suspend fun placesSearchCached(): List<PlaceDTO> = dao.getList().map { it.toDTO() }

	suspend fun clear(){
		dao.clear()
	}

}