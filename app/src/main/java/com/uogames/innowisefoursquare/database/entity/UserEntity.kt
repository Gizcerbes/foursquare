package com.uogames.innowisefoursquare.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
	tableName = "user_table",
	indices = [Index("uid", unique = true)]
)
data class UserEntity(
	@PrimaryKey
	@ColumnInfo("id")
	val id: Int = 0,
	@ColumnInfo("first_name")
	val firstName: String,
	@ColumnInfo("last_name")
	val lastName: String,
	@ColumnInfo("email")
	val email: String,
	@ColumnInfo("created_at")
	val createdAt: Long,
	@ColumnInfo("followers")
	val followers: Int,
	@ColumnInfo("avatar")
	val avatar: String?,
	@ColumnInfo("home_city")
	val homeCity: String,
	@ColumnInfo("uid")
	val uid: String
)