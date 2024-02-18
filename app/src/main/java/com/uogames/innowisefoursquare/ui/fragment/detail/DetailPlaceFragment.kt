package com.uogames.innowisefoursquare.ui.fragment.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselStrategy
import com.google.android.material.carousel.FullScreenCarouselStrategy
import com.squareup.picasso.Picasso
import com.uogames.innowisefoursquare.databinding.FragmentPlaceDetailBinding
import com.uogames.innowisefoursquare.utils.observe
import com.uogames.innowisefoursquare.utils.runIfNull
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class DetailPlaceFragment : Fragment() {

	companion object {
		const val PLACE_ID = "DetailPlaceFragment.PLACE_ID"
	}

	private var _bind: FragmentPlaceDetailBinding? = null
	private val bind get() = _bind!!

	private val vm: DetailPlaceViewModel by viewModels()

	@Inject
	lateinit var picasso: Picasso

	private var observers: Job? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentPlaceDetailBinding.inflate(inflater, container, false)
		return _bind?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		arguments?.getString(PLACE_ID)?.also {
			vm.load(it)
		}
		arguments?.remove(PLACE_ID)

		bind.topAppBar.setNavigationOnClickListener { findNavController().popBackStack() }

	}

	override fun onStart() {
		super.onStart()
		observers = lifecycleScope.launch {
			vm.isLoading.observe(this) {
				bind.mcvProgress.visibility = if (it) View.VISIBLE else View.GONE
			}
			vm.detail.observe(this) {
				bind.cdvName.itemDetail.text = it?.name.correctVisibility(bind.cdvName)
				bind.cdvDescription.itemDetail.text = it?.description.correctVisibility(bind.cdvDescription)
				bind.cdvDistance.itemDetail.text = it?.distance.correctVisibility(bind.cdvDistance)
				bind.cdvRating.itemDetail.text = it?.rating.correctVisibility(bind.cdvRating)
				bind.cdvPhone.itemDetail.text = it?.tel.correctVisibility(bind.cdvPhone)
				bind.cdvWebsite.itemDetail.text = it?.website.correctVisibility(bind.cdvWebsite)
				bind.cdvCoordinates.itemDetail.text = it?.coordinates.correctVisibility(bind.cdvCoordinates)
				bind.cdvAddress.itemDetail.text = it?.address.correctVisibility(bind.cdvAddress)
			}
			vm.photos.observe(this) {
				bind.rvImages.layoutManager = CarouselLayoutManager(FullScreenCarouselStrategy())
				bind.rvImages.adapter = PhotoAdapter(picasso, it)
				bind.rvImages.visibility = if (it.isEmpty()) View.GONE else View.VISIBLE
			}
			vm.isOK.observe(this){ bind.llWrong.visibility = if (it) View.GONE else View.VISIBLE }
		}
	}

	private fun String?.correctVisibility(view: View): String? {
		if (this.isNullOrEmpty()) view.visibility = View.GONE
		else view.visibility = View.VISIBLE
		return this
	}

	override fun onStop() {
		super.onStop()
		observers?.cancel()
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_bind = null
	}


}