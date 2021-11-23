package com.kumail.popularshop.ui.itemslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
    RecyclerView.Adapter<SaleListAdapter.ViewHolder>() {

    private var saleList = emptyList<SaleItem>()
    private var onItemClick: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_sale, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(saleList[position])
    }

    override fun getItemCount(): Int {
        return saleList.size
    }

    fun setSaleList(items: List<SaleItem>) {
        saleList = items
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onClick: (Int) -> Unit) {
        onItemClick = onClick
    }

    inner class ViewHolder(private val binding: ItemSaleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(saleItem: SaleItem) {
            binding.saleItem = saleItem
            binding.ivProduct.loadImage(saleItem.pictures.getPictureUrl(0))

            binding.root.setOnClickListener {
                onItemClick?.let {
                    it(saleItem.id)
                }
            }
        }
    }
}