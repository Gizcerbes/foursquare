package com.uogames.innowisefoursquare.ui.fragment.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.databinding.FragmentNavigationBinding
import com.uogames.innowisefoursquare.utils.observe
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BottomNavigationFragment : Fragment() {

	private var _bind: FragmentNavigationBinding? = null
	private val bind get() = _bind!!

	private val vm: BottomNavigationViewModel by viewModels()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		_bind = FragmentNavigationBinding.inflate(inflater, container, false)
		return _bind?.root
	}

	private var observers: Job? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		bind.bottomBar.setOnItemSelectedListener {
			return@setOnItemSelectedListener vm.position.tryEmit(toPosition(it.itemId))
		}
	}

	override fun onStart() {
		super.onStart()
		val navController = bind.bottomNavGraph.findNavController()
		observers = lifecycleScope.launch {
			vm.position.observe(this) { navigate(navController, it) }
		}
	}

	override fun onStop() {
		super.onStop()
		observers?.cancel()
	}

	private fun navigate(navController: NavController, position: Int) {
		val p = toPosition(bind.bottomBar.selectedItemId)
		if (p > position) leftNavigation(navController, position)
		else if (p < position) rightNavigation(navController, position)
	}

	private fun leftNavigation(navController: NavController, position: Int) {
		navController.navigate(resId = toFragmentIdRes(position), null, navOptions = navOptions {
			anim {
				enter = R.anim.from_left
				exit = R.anim.to_left
			}
		})
	}

	private fun rightNavigation(navController: NavController, position: Int) {
		navController.navigate(resId = toFragmentIdRes(position), null, navOptions = navOptions {
			anim {
				enter = R.anim.from_right
				exit = R.anim.to_right
			}
		})
	}

	private fun toPosition(@IdRes id: Int): Int = when (id) {
		R.id.nearby -> 0
		R.id.history -> 1
		R.id.profile -> 2
		else -> 0
	}


	@IdRes
	private fun toFragmentIdRes(position: Int): Int = when (position) {
		0 -> R.id.placesFragment
		1 -> R.id.historyFragment
		2 -> R.id.profileFragment
		else -> R.id.placesFragment
	}


	override fun onDestroyView() {
		super.onDestroyView()
		_bind = null
	}

}