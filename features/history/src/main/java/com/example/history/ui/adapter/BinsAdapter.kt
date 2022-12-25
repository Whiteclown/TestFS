package com.example.history.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.remote.domain.entity.BinInfo

class BinsAdapter(
    private val onItemClicked: (BinInfo) -> Unit
) : RecyclerView.Adapter<BinViewHolder>() {

    var bins: List<BinInfo>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinViewHolder =
        BinViewHolder.from(parent)

    override fun onBindViewHolder(holder: BinViewHolder, position: Int) {

        bins?.let {
            holder.bind(
                it[position],
                onItemClicked
            )
        }
    }

    override fun getItemCount(): Int = bins?.size ?: 0
}