package com.uogames.innowisefoursquare.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
	tableName = "table_images"
)
data class PhotoEntity(
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo("name")
	val id: Int = 0,
	@ColumnInfo("url")
	val url: String,
	@ColumnInfo("fsq_id", index = true)
	val fsqId: String
)