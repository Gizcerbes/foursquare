package com.uogames.innowisefoursquare.provider

import javax.inject.Inject

class ServiceProvider @Inject constructor(
	private val detailProvider: DetailProvider,
	private val placeProvider: PlaceProvider,
	private val placePhotoProvider: PlacePhotoProvider,
	private val userProvider: UserProvider
){


	suspend fun clean(){
		detailProvider.clear()
		placeProvider.clear()
		placePhotoProvider.clear()
		userProvider.clear()
	}


}


