package com.uogames.innowisefoursquare.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uogames.innowisefoursquare.database.entity.PhotoEntity

@Dao
interface PhotoDAO {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(entity: PhotoEntity): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(entity: List<PhotoEntity>)

	@Delete
	fun delete(entity: PhotoEntity)


	@Query("SELECT * FROM table_images WHERE fsq_id = :fsqId LIMIT :limit")
	fun getByFsqId(fsqId: String, limit: Int = 10): List<PhotoEntity>

	@Query("DELETE FROM table_images")
	fun clear()


}