package com.uogames.innowisefoursquare.ui.fragment.detail

import androidx.lifecycle.ViewModel
import com.uogames.innowisefoursquare.provider.DetailProvider
import com.uogames.innowisefoursquare.provider.PlacePhotoProvider
import com.uogames.innowisefoursquare.provider.dto.PlaceDetailDTO
import com.uogames.innowisefoursquare.provider.dto.PlacePhotoDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPlaceViewModel @Inject constructor(
	private val detailProvider: DetailProvider,
	private val photoProvider: PlacePhotoProvider
) : ViewModel() {

	private val workScope = CoroutineScope(Dispatchers.IO)

	private val _detail = MutableStateFlow<PlaceDetailDTO?>(null)
	val detail = _detail.asStateFlow()

	private val _photos = MutableStateFlow<List<PlacePhotoDTO?>>(listOf())
	val photos = _photos.asStateFlow()

	private val _isLoading = MutableStateFlow(false)
	val isLoading = _isLoading.asStateFlow()

	val isOK = _isLoading
		.combine(_detail){ k, v -> k || v != null}


	fun load(fsqId: String) {
		workScope.launch {
			_isLoading.value = true
			val j = launch { _detail.value = detailProvider.getPlaceDetail(fsqId = fsqId) }
			val m = launch { _photos.value = photoProvider.getPlacePhotos(fsqId = fsqId) }
			j.join()
			m.join()
			_isLoading.value = false
		}
	}

}