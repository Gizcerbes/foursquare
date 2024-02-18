package com.uogames.innowisefoursquare.utils

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.WorkerThread
import androidx.core.graphics.drawable.DrawableCompat
import com.squareup.picasso.Callback
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class PicassoPositiveCallback(private val block: () -> Unit) : Callback {
	override fun onSuccess() = block()


	override fun onError(e: java.lang.Exception?) {}

}

class PicassoNegativeCallback(private val block: (java.lang.Exception?) -> Unit) : Callback {
	override fun onSuccess() {}

	override fun onError(e: java.lang.Exception?) = block(e)

}

fun Picasso.load(url: String?, target: ImageView?, @DrawableRes placeholder: Int) {
	load(url, target, placeholder,null)
}

fun Picasso.load(url: String?, target: ImageView?, @DrawableRes placeholder: Int, @ColorInt tint: Int?) {
	if (url == null) target?.setImageResource(placeholder)
	else load(url)
		.networkPolicy(NetworkPolicy.OFFLINE)
		.into(target, object : Callback {
			override fun onSuccess() {
				if (target?.drawable != null && tint != null) DrawableCompat.setTint(target.drawable, tint)
			}

			override fun onError(e: java.lang.Exception?) {
				load(url)
					.placeholder(placeholder)
					.into(target, object : Callback {
						override fun onSuccess() {
							if (target?.drawable != null && tint != null) DrawableCompat.setTint(target.drawable, tint)
						}

						override fun onError(e: java.lang.Exception?) {
							target?.setImageResource(placeholder)
						}
					})
			}

		})
}

@WorkerThread
suspend fun Picasso.getBitmap(url: String): Bitmap {
	return try {
		load(url)
			.networkPolicy(NetworkPolicy.OFFLINE)
			.get()
	} catch (e: Exception) {
		load(url).get()
	}
}