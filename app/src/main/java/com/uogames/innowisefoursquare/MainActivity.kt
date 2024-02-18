package com.uogames.innowisefoursquare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), SplashEND {

	companion object {
		fun Fragment.navigate(
			@IdRes destination: Int,
			bundle: Bundle? = null,
			navOptions: NavOptions? = getNavOptions()
		) {
			requireActivity().findNavController(R.id.main_nav_graph).navigate(destination, bundle, navOptions)
		}

		private fun getNavOptions(): NavOptions {
			return navOptions {
				anim {
					enter = R.anim.from_top
					exit = R.anim.hide
					popEnter = R.anim.show
					popExit = R.anim.to_top
				}
			}
		}
	}

	private var keepSplash = true
	private var observers: Job? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen().apply { setKeepOnScreenCondition { keepSplash } }
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	override fun onStop() {
		super.onStop()
		Config.save(applicationContext)
	}

	override fun end() {
		keepSplash = false
	}


}

interface SplashEND {
	fun end()
}