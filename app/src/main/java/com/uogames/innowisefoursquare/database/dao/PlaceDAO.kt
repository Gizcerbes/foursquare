package com.uogames.innowisefoursquare.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uogames.innowisefoursquare.database.entity.PlaceEntity

@Dao
interface PlaceDAO {


	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(entity: PlaceEntity): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(entity: List<PlaceEntity>)

	@Delete
	fun delete(entity: PlaceEntity)

	@Query("SELECT * FROM place_table")
	fun getList(): List<PlaceEntity>

	@Query("DELETE FROM place_table")
	fun clear()


}