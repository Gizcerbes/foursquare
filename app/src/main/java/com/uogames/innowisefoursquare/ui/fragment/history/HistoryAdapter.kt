package com.uogames.innowisefoursquare.ui.fragment.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.databinding.CardPlaceBinding
import com.uogames.innowisefoursquare.provider.dto.PlaceDTO
import com.uogames.innowisefoursquare.provider.dto.PlaceDetailDTO
import com.uogames.innowisefoursquare.utils.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.io.Closeable

class HistoryAdapter(
	private val picasso: Picasso,
	private val callSize: () -> Int,
	private val callItem: (position: Int) -> PlaceDetailDTO?
) : RecyclerView.Adapter<HistoryAdapter.Holder>(), Closeable {

	private val scope = CoroutineScope(Dispatchers.Main)

	private val _selectIdFlow = MutableSharedFlow<String>()
	val selectIdFlow = _selectIdFlow.asSharedFlow()


	inner class Holder(private val bind: CardPlaceBinding) : RecyclerView.ViewHolder(bind.root) {

		fun show() {
			val item = callItem(adapterPosition) ?: return
			val color = itemView.context.getColor(R.color.transparent_75)
			picasso.load(item.icon, bind.ivIcon, R.drawable.hide_image_24px, color)
			bind.placeName.text = item.pluralName
			bind.placeType.text = item.name
			bind.root.setOnClickListener { scope.launch { _selectIdFlow.emit(item.fsqID) } }
		}

		fun onDestroy() {
			bind.root.setOnClickListener(null)
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		return Holder(CardPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun getItemCount(): Int = callSize()

	override fun onBindViewHolder(holder: Holder, position: Int) {
		holder.show()
	}

	override fun onViewRecycled(holder: Holder) {
		super.onViewRecycled(holder)
		holder.onDestroy()
	}

	override fun close() {
		scope.cancel()
	}


}