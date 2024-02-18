package com.uogames.innowisefoursquare.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.uogames.innowisefoursquare.database.dao.PhotoDAO
import com.uogames.innowisefoursquare.database.dao.PlaceDAO
import com.uogames.innowisefoursquare.database.dao.PlaceDetailDAO
import com.uogames.innowisefoursquare.database.dao.UserDAO
import com.uogames.innowisefoursquare.database.entity.PhotoEntity
import com.uogames.innowisefoursquare.database.entity.PlaceDetailEntity
import com.uogames.innowisefoursquare.database.entity.PlaceEntity
import com.uogames.innowisefoursquare.database.entity.UserEntity

@Database(
	entities = [
		PhotoEntity::class,
		PlaceDetailEntity::class,
		PlaceEntity::class,
		UserEntity::class
	],
	version = 1
)
abstract class FoursquareDatabase : RoomDatabase() {

	abstract fun photoDAO(): PhotoDAO

	abstract fun placeDetailDAO(): PlaceDetailDAO

	abstract fun placeDAO(): PlaceDAO

	abstract fun userDAO(): UserDAO



}