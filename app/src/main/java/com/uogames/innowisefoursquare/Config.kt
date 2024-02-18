package com.uogames.innowisefoursquare

import android.content.Context
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Config {

	private const val SP_NAME = "LFKDFEWFBNEOEFE"
	private const val ACCESS_TOKEN = "dfFFEGNERROKGR"
	private const val USER_ID = "pkosdfmklfsdk"
	private const val LATITUDE = "fpojwerndewfcv"
	private const val LONGITUDE = "weocxewqovx"


	val accessToken = MutableStateFlow<String?>(null)
	val uid = MutableStateFlow<String?>(null)
	val latitude = MutableStateFlow("53.9057644")
	val longitude = MutableStateFlow("27.669305")


	fun save(context: Context) {
		val sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
		val editor = sp.edit()
		editor.putString(ACCESS_TOKEN, accessToken.value)
		editor.putString(USER_ID, uid.value)
		editor.putString(LATITUDE, latitude.value)
		editor.putString(LONGITUDE, longitude.value)
		editor.apply()
	}

	fun init(context: Context){
		val sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE)
		accessToken.value = sp.getString(ACCESS_TOKEN, null)
		uid.value = sp.getString(USER_ID, null)
		latitude.value = sp.getString(LATITUDE, "53.9057644") ?: latitude.value
		longitude.value = sp.getString(LONGITUDE, "27.669305") ?: longitude.value
	}

}