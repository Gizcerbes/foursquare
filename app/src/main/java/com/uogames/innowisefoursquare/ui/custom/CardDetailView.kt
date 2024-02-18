package com.uogames.innowisefoursquare.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.databinding.CardDetailBinding

class CardDetailView(
	context: Context,
	attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

	private val bind = CardDetailBinding.inflate(LayoutInflater.from(context))

	val itemName = bind.tvItemName
	val itemDetail = bind.tvItemDetail

	init {
		bind.root.apply {
			layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
		}
		addView(bind.root)
		context.theme?.obtainStyledAttributes(
			attrs,
			R.styleable.CardDetailView, 0, 0
		)?.apply {
			itemName.text = getString(R.styleable.CardDetailView_item_name)
			itemDetail.text = getString(R.styleable.CardDetailView_item_detail)
		}?.recycle()

	}


}