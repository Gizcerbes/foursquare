package com.uogames.innowisefoursquare.provider.map

import com.uogames.innowisefoursquare.database.entity.UserEntity
import com.uogames.innowisefoursquare.network.v2.response.UserSelfResponse
import com.uogames.innowisefoursquare.provider.dto.UserSelfDTO

object UserSelfMap {


	fun UserSelfResponse.toDTO() = UserSelfDTO(
		uid = response?.user?.id.orEmpty(),
		firstName = response?.user?.firstName.orEmpty(),
		lastName = response?.user?.lastName.orEmpty(),
		email = response?.user?.contact?.email.orEmpty(),
		createdAt = response?.user?.createdAt?.toLong() ?: 0L,
		followers = response?.user?.followers?.count?.toInt() ?: 0,
		avatar = response?.user?.photo?.let { "${it.prefix}300x300${it.suffix}" }
	)

	fun UserSelfResponse.toEntity() = UserEntity(
		uid = response?.user?.id.orEmpty(),
		firstName = response?.user?.firstName.orEmpty(),
		lastName = response?.user?.lastName.orEmpty(),
		email = response?.user?.contact?.email.orEmpty(),
		createdAt = response?.user?.createdAt?.toLong() ?: 0L,
		followers = response?.user?.followers?.count?.toInt() ?: 0,
		avatar = response?.user?.photo?.let { "${it.prefix}300x300${it.suffix}" },
		homeCity = response?.user?.homeCity.orEmpty()
	)

	fun UserEntity.toDTO() = UserSelfDTO(
		uid = uid,
		firstName = firstName,
		lastName = lastName,
		email = email,
		createdAt = createdAt,
		followers = followers,
		avatar = avatar
	)


}