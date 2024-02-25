package com.uogames.innowisefoursquare.ui.fragment.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.squareup.picasso.Picasso
import com.uogames.innowisefoursquare.MainActivity.Companion.navigate
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.databinding.FragmentProfileBinding
import com.uogames.innowisefoursquare.utils.load
import com.uogames.innowisefoursquare.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

	private var _bind: FragmentProfileBinding? = null
	private val bind get() = _bind!!

	@Inject
	lateinit var picasso: Picasso

	private val vm: ProfileViewModel by viewModels()

	private var observer: Job? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentProfileBinding.inflate(inflater, container, false)
		return _bind?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		bind.btnLogOut.setOnClickListener {
			vm.clean()
			navigate(R.id.emptyFragment)
		}
		vm.load()
		bind.btnRetry.setOnClickListener { vm.load() }
		bind.tilLatitude.editText?.setText(vm.latitude.value)
		bind.tilLongitude.editText?.setText(vm.longitude.value)
		bind.tilLatitude.editText?.addTextChangedListener { vm.setLatitude(it.toString()) }
		bind.tilLongitude.editText?.addTextChangedListener { vm.setLongitude(it.toString()) }
	}

	@SuppressLint("SetTextI18n")
	override fun onStart() {
		super.onStart()
		observer = lifecycleScope.launch {
			vm.user.observe(this) {
				if (it == null) return@observe
				val calendar = Calendar.getInstance().apply { timeInMillis = it.createdAt * 1000 }
				bind.tvFirstName.text = it.firstName
				bind.tvLastName.text = it.lastName
				bind.tvEmail.text = it.email
				bind.tvCreatedAt.text = "${getString(R.string.created_at)} ${calendar.time}"
				bind.tvFollowers.text = "${getString(R.string.followers)} ${it.followers}"
				picasso.load(it.avatar, bind.ivAvatar, R.drawable.hide_image_24px)
			}
			vm.isOk.observe(this) { bind.llWrong.visibility = if (it) View.GONE else View.VISIBLE }
			vm.isLoading.observe(this) { bind.mcvProgress.visibility = if (it) View.VISIBLE else View.GONE }
		}
	}

	override fun onStop() {
		super.onStop()
		observer?.cancel()
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_bind = null
	}

}