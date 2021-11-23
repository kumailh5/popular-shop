package com.kumail.popularshop.ui.itemdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kumail.popularshop.R
import com.kumail.popularshop.data.model.Picture
import com.kumail.popularshop.databinding.ItemImageCarouselBinding
import com.kumail.popularshop.util.getPictureUrl
import com.kumail.popularshop.util.loadImage
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumailhussain on 17/10/2021.
 */
@Singleton
class ImagePagerAdapter @Inject constructor() :
    ListAdapter<Picture, ImagePagerAdapter.ViewHolder>(ImagesListDiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_image_carousel,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position).getPictureUrl())
    }

    inner class ViewHolder(private val binding: ItemImageCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageUrl: String) {
            binding.ivCarousel.loadImage(imageUrl)
        }
    }
}

object ImagesListDiffCallback : DiffUtil.ItemCallback<Picture>() {
    override fun areItemsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Picture, newItem: Picture): Boolean {
        return oldItem == newItem
    }
}