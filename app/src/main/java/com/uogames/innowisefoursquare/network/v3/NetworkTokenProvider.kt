package com.uogames.innowisefoursquare.network.v3

import com.uogames.innowisefoursquare.network.v3.response.AccessTokenResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import javax.inject.Inject
import javax.inject.Named

class NetworkTokenProvider @Inject constructor(
	@Named("user_api") private val client: HttpClient
) {

	suspend fun accessToken(
		clientID: String,
		clientSecret: String,
		grantType: String = "authorization_code",
		redirectUri: String,
		code: String
	) = client.post("https://foursquare.com/oauth2/access_token") {
		parameter("client_id", clientID)
		parameter("client_secret", clientSecret)
		parameter("grant_type", grantType)
		parameter("redirect_uri", redirectUri)
		parameter("code", code)
	}.body<AccessTokenResponse>()


}