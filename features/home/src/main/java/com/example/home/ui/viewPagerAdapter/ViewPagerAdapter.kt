package com.example.home.ui.viewPagerAdapter

import android.net.Uri
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.home.R
import com.example.home.ui.viewPagerAdapter.viewholder.BaseViewHolder
import com.example.home.ui.viewPagerAdapter.viewholder.InfoViewHolder
import com.example.home.ui.viewPagerAdapter.viewholder.SearchViewHolder
import com.example.remote.domain.entity.BinInfo

class ViewPagerAdapter(
    private val onSearchBtnClicked: (Int) -> Unit,
    private val onHistoryBtnClicked: () -> Unit,
    private val onHyperClicked: (Uri) -> Unit,
) : RecyclerView.Adapter<BaseViewHolder>() {

    var binInfo: BinInfo? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int): Int = when (position) {

        0 -> R.layout.page_search

        1 -> R.layout.page_info

        else -> throw Exception("Unknown view")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {

            R.layout.page_search -> SearchViewHolder.from(parent)

            R.layout.page_info -> InfoViewHolder.from(parent)

            else -> throw Exception("Invalid view type")
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder) {
            is SearchViewHolder -> {
                holder.bind(
                    onSearchBtnClicked,
                    onHistoryBtnClicked
                )
            }

            is InfoViewHolder -> {
                binInfo?.let {
                    holder.bind(
                        it,
                        onHyperClicked
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int = 2
}