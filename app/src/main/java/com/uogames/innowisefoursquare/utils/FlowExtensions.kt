package com.uogames.innowisefoursquare.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.launchAndCollectIn(
	owner: LifecycleOwner,
	minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
	crossinline action: suspend CoroutineScope.(T) -> Unit
) = owner.lifecycleScope.launch {
	owner.repeatOnLifecycle(minActiveState) {
		collect {
			action(it)
		}
	}
}

fun <T> Flow<T>.observe(
	owner: CoroutineScope,
	collector: FlowCollector<T>
) = owner.launch { collect(collector) }