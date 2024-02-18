package com.uogames.innowisefoursquare.provider.map

import com.uogames.innowisefoursquare.database.entity.PlaceDetailEntity
import com.uogames.innowisefoursquare.network.v3.response.PlaceDetailResponse
import com.uogames.innowisefoursquare.provider.dto.PlaceDetailDTO

object PlaceDetailMap {

	fun PlaceDetailResponse.toDTO() = PlaceDetailDTO(
		name = name.orEmpty(),
		description = description.orEmpty(),
		distance = distance?.toString().orEmpty(),
		rating = rating?.toString().orEmpty(),
		tel = tel.orEmpty(),
		website = website.orEmpty(),
		coordinates = geocodes?.main?.let { "${it.latitude},${it.longitude}" }.orEmpty(),
		address = location?.address.orEmpty(),
		icon = categories!![0]?.icon?.let { "${it.prefix}120${it.suffix}" }.orEmpty(),
		fsqID = fsqId.orEmpty(),
		pluralName = categories[0]?.pluralName.orEmpty(),
	)

	fun PlaceDetailResponse.toEntity() = PlaceDetailEntity(
		name = name.orEmpty(),
		description = description.orEmpty(),
		rating = rating?.toString().orEmpty(),
		tel = tel.orEmpty(),
		website = website.orEmpty(),
		latitude = geocodes?.main?.latitude?.toFloat() ?: 0f,
		longitude = geocodes?.main?.longitude?.toFloat() ?: 0f,
		address = location?.address.orEmpty(),
		fsqId = fsqId.orEmpty(),
		icon = runCatching { categories!![0]?.icon?.let { "${it.prefix}120${it.suffix}" } }.getOrNull(),
		pluralName = runCatching { categories!![0]?.pluralName }.getOrNull().orEmpty()
	)

	fun PlaceDetailEntity.toDTO() = PlaceDetailDTO(
		name = name,
		description = description,
		distance = "",
		rating = rating,
		tel = tel,
		website = website,
		coordinates = "$latitude,$longitude",
		address = address,
		icon = icon.orEmpty(),
		fsqID = fsqId,
		pluralName = pluralName,
	)


}