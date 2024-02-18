package com.uogames.innowisefoursquare.ui.fragment.authentication

import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResult
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uogames.innowisefoursquare.BuildConfig
import com.uogames.innowisefoursquare.Config
import com.uogames.innowisefoursquare.network.v3.NetworkTokenProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import net.openid.appauth.TokenRequest
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
	private val tokenProvider: NetworkTokenProvider
): ViewModel() {

	private val AUTH_URI = "https://foursquare.com/oauth2/authenticate"
	private val TOKEN_URI = "https://foursquare.com/oauth2/access_token"
	private val CALLBACK_URL = "com.uogames.oauth://callback"
	private val SCOPE = "user,repo"
	private val RESPONSE_TYPE = ResponseTypeValues.CODE
	private val CLIENT_SECRET = BuildConfig.CLIENT_SECRET
	private val CLIENT_ID = BuildConfig.CLIENT_ID


	private val _errorHandler  = MutableSharedFlow<Throwable>()
	val errorHandler = _errorHandler.asSharedFlow()

	private val _isLoading = MutableStateFlow(false)
	val isLoading = _isLoading.asStateFlow()

	fun createAuthIntent(authService: AuthorizationService): Intent {
		val i = CustomTabsIntent.Builder().build()
		val req = getAuthRequest()
		return authService.getAuthorizationRequestIntent(req, i)
	}

	fun createAuthRequest(r: ActivityResult) {
		_isLoading.value = true
		val dataIntent = r.data ?: return
		val exception = AuthorizationException.fromIntent(dataIntent)
		val tokenExchangeRequest = AuthorizationResponse.fromIntent(dataIntent)?.createTokenExchangeRequest()
		when {
			exception != null -> viewModelScope.launch {
				_errorHandler.emit(exception)
				_isLoading.value = false
			}
			tokenExchangeRequest != null -> tokenRequest(tokenExchangeRequest)
		}
	}

	private fun tokenRequest(tokenRequest: TokenRequest) {
		viewModelScope.launch {
			runCatching {
				tokenProvider.accessToken(
					clientID = CLIENT_ID,
					clientSecret = CLIENT_SECRET,
					redirectUri = CALLBACK_URL,
					code = tokenRequest.authorizationCode.orEmpty()
				)
			}.onFailure {
				_errorHandler.emit(it)
			}.onSuccess {
				Config.accessToken.value = it.accessToken
			}
			_isLoading.value = false
		}
	}

	private fun buildConfiguration() = AuthorizationServiceConfiguration(
		Uri.parse(AUTH_URI),
		Uri.parse(TOKEN_URI)
	)

	private fun getAuthRequest(): AuthorizationRequest {
		val redirectUri = CALLBACK_URL.toUri()
		return AuthorizationRequest.Builder(
			buildConfiguration(),
			CLIENT_ID,
			RESPONSE_TYPE,
			redirectUri
		).setScope(SCOPE).build()
	}



}