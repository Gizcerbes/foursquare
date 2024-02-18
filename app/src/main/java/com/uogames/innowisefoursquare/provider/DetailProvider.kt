package com.uogames.innowisefoursquare.provider

import com.uogames.innowisefoursquare.database.dao.PlaceDetailDAO
import com.uogames.innowisefoursquare.network.v3.NetworkPlaceDetailProvider
import com.uogames.innowisefoursquare.provider.dto.PlaceDetailDTO
import com.uogames.innowisefoursquare.provider.map.PlaceDetailMap.toDTO
import com.uogames.innowisefoursquare.provider.map.PlaceDetailMap.toEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class DetailProvider @Inject constructor(
	private val provider: NetworkPlaceDetailProvider,
	private val dao: PlaceDetailDAO
) {

	suspend fun getPlaceDetail(
		fsqId: String,
		fields: String? = null,
		sessionToken: String? = null
	): PlaceDetailDTO? {
		return try {
			val dt = provider.getPlaceDetail(fsqId, fields, sessionToken)
			withContext(coroutineContext) { dao.insert(dt.toEntity()) }
			dt.toDTO()
		} catch (e: Throwable) {
			dao.getByFsqId(fsqId)?.toDTO()
		}
	}

	suspend fun getPlaceDetailCached(
		fsqId: String
	): PlaceDetailDTO? = dao.getByFsqId(fsqId)?.toDTO()

	suspend fun getPlaces() = dao.getDetails().map { it.toDTO() }

	suspend fun clear() {
		dao.clear()
	}
}