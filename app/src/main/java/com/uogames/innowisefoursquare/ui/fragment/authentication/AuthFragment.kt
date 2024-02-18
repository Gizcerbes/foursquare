package com.uogames.innowisefoursquare.ui.fragment.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.uogames.innowisefoursquare.Config
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.databinding.FragmentAuthBinding
import com.uogames.innowisefoursquare.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationService


@AndroidEntryPoint
class AuthFragment : Fragment() {

	private var _bind: FragmentAuthBinding? = null
	private val binding get() = _bind!!

	private val vm: AuthViewModel by viewModels()
	private val authService by lazy { AuthorizationService(requireContext()) }

	private val getAuthResponse = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
		vm.createAuthRequest(it)
	}

	private var observers: Job? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentAuthBinding.inflate(inflater, container, false)
		return _bind?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.btn.setOnClickListener {
			getAuthResponse.launch(vm.createAuthIntent(authService))
		}
	}

	override fun onStart() {
		super.onStart()
		observers = lifecycleScope.launch {
			vm.errorHandler.observe(this){
				Snackbar.make(requireView(), it.message ?: it.javaClass.name , Snackbar.LENGTH_SHORT).show()
			}
			vm.isLoading.observe(this){
				binding.lpiIndicator.visibility = if (it) View.VISIBLE else View.GONE
				binding.btn.isEnabled = !it
			}
			Config.accessToken.observe(this){
				if (it != null) findNavController().navigate(R.id.emptyFragment)
			}
		}
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