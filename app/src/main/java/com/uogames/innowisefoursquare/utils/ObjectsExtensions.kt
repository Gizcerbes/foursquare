package com.uogames.innowisefoursquare.utils

inline fun <T> T?.runIfNull(body: () -> Unit): T? {
	if (this == null) body()
	return this
}