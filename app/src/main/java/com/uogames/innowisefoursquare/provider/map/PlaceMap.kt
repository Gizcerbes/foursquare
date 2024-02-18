package com.uogames.innowisefoursquare.provider.map

import com.uogames.innowisefoursquare.database.entity.PlaceEntity
import com.uogames.innowisefoursquare.network.v3.response.SearchResponse
import com.uogames.innowisefoursquare.provider.dto.PlaceDTO

object PlaceMap {

	fun SearchResponse.Result.toDTO(): PlaceDTO {
		val category = categories[0]
		return PlaceDTO(
			icon = "${category.icon.prefix}120${category.icon.suffix}",
			name = category.pluralName,
			type = name,
			fsqId = fsqId
		)
	}

	fun SearchResponse.Result.toEntity(): PlaceEntity {
		val category = categories[0]
		return PlaceEntity(
			icon = "${category.icon.prefix}120${category.icon.suffix}",
			name = category.pluralName,
			type = name,
			fsqId = fsqId,
			latitude = geocodes.main.latitude.toFloat(),
			longitude = geocodes.main.longitude.toFloat()
		)
	}

	fun PlaceEntity.toDTO(): PlaceDTO = PlaceDTO(
		icon = icon,
		name = name,
		type = type,
		fsqId = fsqId,
	)


}