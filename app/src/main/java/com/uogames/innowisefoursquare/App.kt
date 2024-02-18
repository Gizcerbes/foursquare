package com.uogames.innowisefoursquare

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {


	override fun onCreate() {
		super.onCreate()
		Config.init(this)
	}


}