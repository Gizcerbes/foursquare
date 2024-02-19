package com.uogames.innowisefoursquare.provider.map

import com.uogames.innowisefoursquare.database.entity.PhotoEntity
import com.uogames.innowisefoursquare.network.v3.response.PlacePhotoResponse
import com.uogames.innowisefoursquare.provider.dto.PlacePhotoDTO

object PlacePhotoMap {


	fun PlacePhotoResponse.toDTO() = map { it.toPhotoDTO() }


	fun PlacePhotoResponse.PlacePhotoItem.toPhotoDTO() = PlacePhotoDTO(
		id = id.orEmpty(),
		url = "${prefix}original$suffix"
	)

	fun PlacePhotoResponse.toEntity(fsqID: String) = map { it.toEntity(fsqID)  }

	fun PlacePhotoResponse.PlacePhotoItem.toEntity(fsqID: String) = PhotoEntity(
		id = id?.toIntOrNull() ?: 0,
		url = "${prefix}original$suffix",
		fsqId = fsqID
	)

	fun PhotoEntity.toDTO() = PlacePhotoDTO(
		id = id.toString(),
		url = url
	)

}