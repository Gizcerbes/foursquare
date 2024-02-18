package com.uogames.innowisefoursquare.ui.fragment.profile

import androidx.lifecycle.ViewModel
import com.uogames.innowisefoursquare.Config
import com.uogames.innowisefoursquare.provider.ServiceProvider
import com.uogames.innowisefoursquare.provider.UserProvider
import com.uogames.innowisefoursquare.provider.dto.UserSelfDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
	private val provider: UserProvider,
	private val serviceProvider: ServiceProvider
) : ViewModel() {

	private val workScope = CoroutineScope(Dispatchers.IO)

	private val _user = MutableStateFlow<UserSelfDTO?>(null)
	val user = _user.asStateFlow()

	private val _isLoading = MutableStateFlow(false)
	val isLoading = _isLoading.asStateFlow()

	val isOk = _isLoading
		.combine(_user) { k, v -> k || v != null }

	fun load() {
		_isLoading.value = true
		workScope.launch {
			runCatching {
				provider.getUserSelf(Config.uid.value)
			}.onSuccess {
				_user.value = it
				Config.uid.value = it?.uid
			}
			_isLoading.value = false
		}
	}

	fun clean() {
		_isLoading.value = true
		workScope.launch {
			serviceProvider.clean()
			_isLoading.value = false
		}
	}


}