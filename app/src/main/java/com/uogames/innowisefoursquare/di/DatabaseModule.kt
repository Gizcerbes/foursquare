package com.uogames.innowisefoursquare.di

import android.content.Context
import androidx.room.Room
import com.uogames.innowisefoursquare.database.FoursquareDatabase
import com.uogames.innowisefoursquare.database.dao.PhotoDAO
import com.uogames.innowisefoursquare.database.dao.PlaceDAO
import com.uogames.innowisefoursquare.database.dao.PlaceDetailDAO
import com.uogames.innowisefoursquare.database.dao.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

	private val DATABASE_NAME = "foursquare"

	@Singleton
	@Provides
	fun provideDatabase(@ApplicationContext context: Context): FoursquareDatabase = Room
		.databaseBuilder(context, klass = FoursquareDatabase::class.java, DATABASE_NAME)
		.build()

	@Provides
	fun providePhotoDAO(database: FoursquareDatabase):PhotoDAO = database.photoDAO()

	@Provides
	fun providePlaceDetailDAO(database: FoursquareDatabase): PlaceDetailDAO = database.placeDetailDAO()

	@Provides
	fun providePlaceDAO(database: FoursquareDatabase): PlaceDAO = database.placeDAO()

	@Provides
	fun provideUserDAO(database: FoursquareDatabase): UserDAO = database.userDAO()

}