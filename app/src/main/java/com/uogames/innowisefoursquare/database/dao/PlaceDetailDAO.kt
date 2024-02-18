package com.uogames.innowisefoursquare.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uogames.innowisefoursquare.database.entity.PlaceDetailEntity

@Dao
interface PlaceDetailDAO {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(entity: PlaceDetailEntity): Long

	@Delete
	fun delete(entity: PlaceDetailEntity)

	@Query("SELECT * FROM place_detail_table WHERE fsq_id = :fsqId")
	fun getByFsqId(fsqId: String): PlaceDetailEntity?

	@Query("DELETE FROM place_detail_table")
	fun clear()

	@Query("SELECT * FROM place_detail_table")
	fun getDetails(): List<PlaceDetailEntity>

}