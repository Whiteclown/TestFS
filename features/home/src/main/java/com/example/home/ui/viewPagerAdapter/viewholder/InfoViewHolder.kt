package com.example.home.ui.viewPagerAdapter.viewholder

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.home.R
import com.example.home.databinding.PageInfoBinding
import com.example.remote.domain.entity.BinInfo

class InfoViewHolder(
    private val binding: PageInfoBinding,
) : BaseViewHolder(binding.root) {

    fun bind(
        item: BinInfo,
        onHyperClicked: (Uri) -> Unit,
    ) {
        with(binding) {

            tvScheme.text = item.scheme ?: binding.root.context.getString(R.string.no_data)
            tvBrand.text = item.brand ?: binding.root.context.getString(R.string.no_data)
            tvType.text = item.type ?: binding.root.context.getString(R.string.no_data)
            item.prepaid?.let {
                tvPrepaid.text = if (it) {
                    binding.root.context.getString(R.string.yes)
                } else {
                    binding.root.context.getString(R.string.no)
                }
            } ?: run {
                tvPrepaid.text = binding.root.context.getString(R.string.no_data)
            }
            tvCardNumberLength.text =
                item.number?.length?.toString() ?: binding.root.context.getString(R.string.no_data)
            item.number?.luhn?.let {
                tvCardNumberLuhn.visibility = View.VISIBLE
                tvCardNumberLuhn.text = if (it) {
                    binding.root.context.getString(R.string.yes)
                } else {
                    binding.root.context.getString(R.string.no)
                }
            } ?: run {
                tvCardNumberLuhn.text = binding.root.context.getString(R.string.no_data)
            }
            tvCountry.text = item.country?.alpha2?.let { alpha2 ->
                item.country?.name?.let { name ->
                    binding.root.context.getString(
                        R.string.country_text,
                        alpha2,
                        name
                    )
                } ?: run {
                    alpha2
                }
            } ?: run {
                item.country?.name ?: binding.root.context.getString(R.string.no_data)
            }
            tvBankNameAndCity.text = item.bank?.name?.let { name ->
                item.bank?.city?.let { city ->
                    binding.root.context.getString(
                        R.string.bank_name_and_city,
                        name,
                        city
                    )
                } ?: run {
                    name
                }
            } ?: run {
                item.bank?.city ?: binding.root.context.getString(R.string.no_data)
            }
            tvBankUrl.text = item.bank?.url ?: binding.root.context.getString(R.string.no_data)
            tvBankPhone.text = item.bank?.phone ?: binding.root.context.getString(R.string.no_data)
            tvLatitudeAndLongitude.text = binding.root.context.getString(
                R.string.latitude_and_longitude,
                item.country?.latitude,
                item.country?.longitude
            )
            tvLatitudeAndLongitude.text = item.country?.latitude?.let { latitude ->
                item.country?.longitude?.let { longitude ->
                    binding.root.context.getString(
                        R.string.latitude_and_longitude,
                        latitude,
                        longitude
                    )
                } ?: binding.root.context.getString(R.string.no_data)
            } ?: binding.root.context.getString(R.string.no_data)

            item.country?.latitude?.let { latitude ->
                item.country?.longitude?.let { longitude ->
                    tvLatitudeAndLongitude.setOnClickListener {
                        val uri = Uri.parse("geo:$latitude,$longitude")
                        onHyperClicked(uri)
                    }
                }
            }
            item.bank?.phone?.let {
                tvBankPhone.setOnClickListener {
                    val uri = Uri.parse("tel:$it")
                    onHyperClicked(uri)
                }
            }
            item.bank?.url?.let {
                tvBankUrl.setOnClickListener {
                    val uri = Uri.parse("http://$it")
                    onHyperClicked(uri)
                }
            }
        }
    }

    companion object {

        fun from(parent: ViewGroup): InfoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = PageInfoBinding.inflate(layoutInflater, parent, false)
            return InfoViewHolder(binding)
        }
    }
}