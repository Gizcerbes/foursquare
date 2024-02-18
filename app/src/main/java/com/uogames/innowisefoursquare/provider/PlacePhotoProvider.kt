package com.uogames.innowisefoursquare.provider

import com.uogames.innowisefoursquare.database.dao.PhotoDAO
import com.uogames.innowisefoursquare.network.v3.NetworkPhotosProvider
import com.uogames.innowisefoursquare.provider.dto.PlacePhotoDTO
import com.uogames.innowisefoursquare.provider.map.PlacePhotoMap.toDTO
import com.uogames.innowisefoursquare.provider.map.PlacePhotoMap.toEntity
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class PlacePhotoProvider @Inject constructor(
	private val provider: NetworkPhotosProvider,
	private val dao: PhotoDAO
) {

	suspend fun getPlacePhotos(
		fsqId: String,
		limit: Int? = null,
		sort: String? = null,
		classifications: String? = null
	): List<PlacePhotoDTO> {
		return try {
			val data = provider.getPlacePhotos(fsqId, limit, sort, classifications)
			withContext(coroutineContext) { dao.insert(data.toEntity(fsqId)) }
			data.toDTO()
		} catch (e: Throwable) {
			dao.getByFsqId(fsqId, limit ?: 10).map { it.toDTO() }
		}
	}

	suspend fun getPlacePhotoCached(
		fsqId: String
	) = dao.getByFsqId(fsqId).map { it.toDTO() }


	suspend fun clear(){
		dao.clear()
	}

}