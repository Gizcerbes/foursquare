package com.uogames.innowisefoursquare.ui.fragment.empty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.uogames.innowisefoursquare.Config
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.SplashEND

class EmptyFragment : Fragment() {

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return RelativeLayout(requireContext())
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val navHost = requireActivity().findNavController(R.id.main_nav_graph)
		if (Config.accessToken.value != null) {
			val graph = navHost.navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(R.id.bottomNavigationFragment) }
			navHost.setGraph(graph, null)
		} else {
			val graph = navHost.navInflater.inflate(R.navigation.nav_graph).apply { setStartDestination(R.id.authFragment) }
			navHost.setGraph(graph, null)
		}
		(requireContext() as SplashEND).end()
	}

}