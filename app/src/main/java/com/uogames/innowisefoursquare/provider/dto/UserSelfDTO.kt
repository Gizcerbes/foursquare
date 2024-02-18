package com.uogames.innowisefoursquare.provider.dto

data class UserSelfDTO(
	val uid: String,
	val firstName: String,
	val lastName: String,
	val email: String,
	val createdAt: Long,
	val followers: Int,
	val avatar: String?

)