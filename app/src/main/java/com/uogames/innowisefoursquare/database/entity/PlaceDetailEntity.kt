package com.uogames.innowisefoursquare.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(
	tableName = "place_detail_table",
	indices = [Index("fsq_id", unique = true)]
)
data class PlaceDetailEntity(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo("id")
	val id: Int = 0,
	@ColumnInfo("name")
	val name: String,
	@ColumnInfo("description")
	val description: String,
	@ColumnInfo("rating")
	val rating: String,
	@ColumnInfo("tel")
	val tel: String,
	@ColumnInfo("website")
	val website: String,
	@ColumnInfo("latitude")
	val latitude: Float,
	@ColumnInfo("longitude")
	val longitude: Float,
	@ColumnInfo("address")
	val address: String,
	@ColumnInfo("fsq_id")
	val fsqId: String,
	@ColumnInfo("icon")
	val icon: String?,
	@ColumnInfo("plural_name")
	val pluralName: String
)