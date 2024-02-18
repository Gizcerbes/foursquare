package com.uogames.innowisefoursquare.network.v2

import android.annotation.SuppressLint
import com.uogames.innowisefoursquare.network.v2.response.UserSelfResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import javax.inject.Named


class NetworkUserProvider @Inject constructor(
	@Named("user_api") private val client: HttpClient
) {

	@SuppressLint("SimpleDateFormat")
	suspend fun getUserSelf() = client.get("users/self"){
		val time = SimpleDateFormat("YYYYMMDD").format(Date())
		parameter("v", time)
	}.body<UserSelfResponse>()


}