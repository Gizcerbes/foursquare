package com.uogames.innowisefoursquare.ui.fragment.places

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.uogames.innowisefoursquare.MainActivity.Companion.navigate
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.databinding.FragmentPlacesBinding
import com.uogames.innowisefoursquare.ui.fragment.detail.DetailPlaceFragment
import com.uogames.innowisefoursquare.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlacesFragment : Fragment() {

	companion object {
		const val REFRESH = "PlacesFragment.REFRESH"
	}

	private var _bind: FragmentPlacesBinding? = null
	private val bind get() = _bind!!

	private val vm: PlacesViewModel by viewModels()

	private var observers: Job? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentPlacesBinding.inflate(inflater, container, false)
		return _bind?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val refresh = arguments?.getString(REFRESH)
		arguments?.remove(REFRESH)
		bind.rvItems.layoutManager = GridLayoutManager(requireContext(), 1)
		bind.rvItems.adapter = vm.adapter
		bind.rvItems.layoutManager?.onRestoreInstanceState(vm.recyclerState)
		bind.srlRefresh.setOnRefreshListener { vm.load() }
		if (refresh != null || vm.dataList.value.isEmpty()) vm.load()
	}

	override fun onStart() {
		super.onStart()
		observers = lifecycleScope.launch {
			vm.adapter.selectIdFlow.observe(this) {
				navigate(R.id.detailPlaceFragment, bundleOf(DetailPlaceFragment.PLACE_ID to it))
			}
			vm.isLoading.observe(this) { bind.srlRefresh.isRefreshing = it }
			vm.isOk.observe(this) { bind.llWrong.visibility = if (it) View.GONE else View.VISIBLE }
		}
	}

	override fun onStop() {
		super.onStop()
		observers?.cancel()
		vm.recyclerState = bind.rvItems.layoutManager?.onSaveInstanceState()
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_bind?.rvItems?.adapter = null
		_bind = null
	}


}