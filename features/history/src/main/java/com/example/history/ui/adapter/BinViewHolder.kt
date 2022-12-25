package com.example.history.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.history.databinding.ItemBinBinding
import com.example.remote.domain.entity.BinInfo

class BinViewHolder(
    private val binding: ItemBinBinding,
) : ViewHolder(binding.root) {

    fun bind(
        item: BinInfo,
        onItemClicked: (BinInfo) -> Unit
    ) {
        with(binding) {
            tvBinNumber.text = item.bin.toString()
            itemView.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    companion object {

        fun from(parent: ViewGroup): BinViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemBinBinding.inflate(layoutInflater, parent, false)
            return BinViewHolder(binding)
        }
    }
}