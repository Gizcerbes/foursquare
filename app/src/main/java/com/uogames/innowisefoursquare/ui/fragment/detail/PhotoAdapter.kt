package com.uogames.innowisefoursquare.ui.fragment.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.uogames.innowisefoursquare.R
import com.uogames.innowisefoursquare.databinding.CardCarouselImageBinding
import com.uogames.innowisefoursquare.provider.dto.PlacePhotoDTO
import com.uogames.innowisefoursquare.utils.load

class PhotoAdapter(
	private val picasso: Picasso,
	private val list: List<PlacePhotoDTO?>
): RecyclerView.Adapter<PhotoAdapter.Holder>() {

	inner class Holder(private val bind: CardCarouselImageBinding): RecyclerView.ViewHolder(bind.root){

		fun show(){
			picasso.load(list[adapterPosition]?.url, bind.carouselImageView, R.drawable.hide_image_24px)
		}

	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		return Holder(CardCarouselImageBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun getItemCount(): Int = list.size

	override fun onBindViewHolder(holder: Holder, position: Int) {
		holder.show()
	}


}