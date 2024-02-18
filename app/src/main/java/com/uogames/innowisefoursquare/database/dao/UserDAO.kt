package com.uogames.innowisefoursquare.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uogames.innowisefoursquare.database.entity.UserEntity

@Dao
interface UserDAO {


	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(entity: UserEntity): Long

	@Delete
	suspend fun delete(entity: UserEntity)


	@Query("DELETE FROM user_table")
	fun clear()

	@Query("SELECT * FROM user_table WHERE uid = :uid")
	fun getByUID(uid: String): UserEntity?


}