package com.uogames.innowisefoursquare.ui.fragment.history

import android.os.Parcelable
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.uogames.innowisefoursquare.provider.DetailProvider
import com.uogames.innowisefoursquare.provider.PlaceProvider
import com.uogames.innowisefoursquare.provider.dto.PlaceDTO
import com.uogames.innowisefoursquare.provider.dto.PlaceDetailDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel  @Inject constructor(
	private val provider: DetailProvider,
	picasso: Picasso
) : ViewModel() {

	private val workScope = CoroutineScope(Dispatchers.IO)

	val query = MutableStateFlow<String?>(null)

	private val _dataList = MutableStateFlow<List<PlaceDetailDTO>>(listOf())
	val dataList get() = _dataList.asStateFlow()

	private val _isLoading = MutableStateFlow(false)
	val isLoading = _isLoading.asStateFlow()

	private val _errorHandler = MutableSharedFlow<Throwable>()
	val errorHandler = _errorHandler.asSharedFlow()

	val isOk = _dataList
		.combine(_isLoading) { k, v -> (k.isNotEmpty()) || v }

	var recyclerState: Parcelable? = null

	val adapter = HistoryAdapter(
		picasso = picasso,
		callSize = { runCatching { _dataList.value.size }.getOrNull() ?: 0 },
		callItem = { runCatching { _dataList.value[it] }.getOrNull() }
	)


	fun load() {
		workScope.launch {
			_isLoading.value = true
			runCatching {
				provider.getPlaces()
			}.onSuccess {
				_dataList.value = it
				launch(Dispatchers.Main) { adapter.notifyDataSetChanged() }
			}.onFailure {
				_errorHandler.emit(it)
			}
			_isLoading.value = false
		}
	}


}