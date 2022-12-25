package com.example.home.ui.viewPagerAdapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import com.example.home.databinding.PageSearchBinding

class SearchViewHolder(
    private val binding: PageSearchBinding,
) : BaseViewHolder(binding.root) {

    fun bind(
        onSearchBtnClicked: (Int) -> Unit,
        onHistoryBtnClicked: () -> Unit,
    ) {
        with(binding) {
            tfBin.editText?.doOnTextChanged { text, _, _, _ ->
                if (text?.length == 0) {
                    tfBin.error = "BIN's length must be more than 0!"
                    tfBin.isErrorEnabled = true
                    fbSearch.isEnabled = false
                } else {
                    tfBin.isErrorEnabled = false
                    fbSearch.isEnabled = true
                }
            }

            fbSearch.setOnClickListener {
                val bin = tfBin.editText?.text.toString()
                if (bin != "") {
                    (itemView.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                        .hideSoftInputFromWindow(
                            tfBin.windowToken,
                            0
                        )
                    onSearchBtnClicked(bin.toInt())
                }
            }
            fbHistory.setOnClickListener {
                onHistoryBtnClicked()
            }
        }
    }

    companion object {

        fun from(parent: ViewGroup): SearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = PageSearchBinding.inflate(layoutInflater, parent, false)
            return SearchViewHolder(binding)
        }
    }
}