package com.kumail.popularshop.ui.itemslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kumail.popularshop.R
import com.kumail.popularshop.data.model.SaleItem
import com.kumail.popularshop.databinding.ItemSaleBinding
import com.kumail.popularshop.util.getPictureUrl
import com.kumail.popularshop.util.loadImage
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by kumailhussain on 15/10/2021.
 */
@Singleton
class SaleListAdapter @Inject constructor() :
    ListAdapter<SaleItem, SaleListAdapter.ViewHolder>(SaleListDiffCallback) {

    private var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_sale, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setOnItemClickListener(onClick: (Int) -> Unit) {
        onItemClick = onClick
    }

    inner class ViewHolder(private val binding: ItemSaleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(saleItem: SaleItem) {
            binding.saleItem = saleItem
            binding.ivProduct.loadImage(saleItem.pictures[0].getPictureUrl())

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(saleItem.id)
                }
            }
        }
    }
}

object SaleListDiffCallback : DiffUtil.ItemCallback<SaleItem>() {
    override fun areItemsTheSame(oldItem: SaleItem, newItem: SaleItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SaleItem, newItem: SaleItem): Boolean {
        return oldItem == newItem
    }
}