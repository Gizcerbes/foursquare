package com.uogames.innowisefoursquare.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
	tableName = "place_table",
	indices = [Index("fsq_id", unique = true)]
)
data class PlaceEntity(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo("id")
	val id: Int = 0,
	@ColumnInfo("icon")
	val icon: String,
	@ColumnInfo("name")
	val name: String,
	@ColumnInfo("type")
	val type: String,
	@ColumnInfo("fsq_id")
	val fsqId: String,
	@ColumnInfo("latitude")
	val latitude: Float,
	@ColumnInfo("longitude")
	val longitude: Float
)