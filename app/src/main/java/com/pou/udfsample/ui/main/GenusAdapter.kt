package com.pou.udfsample.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.pou.udfsample.R
import com.pou.udfsample.databinding.FruitItemViewBinding
import com.pou.udfsample.ui.main.common.viewBinding

class GenusAdapter : ListAdapter<GenusPickerData, ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback: DiffUtil.ItemCallback<GenusPickerData> =
            object : DiffUtil.ItemCallback<GenusPickerData>() {
                override fun areItemsTheSame(
                    oldItem: GenusPickerData,
                    newItem: GenusPickerData
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: GenusPickerData,
                    newItem: GenusPickerData
                ): Boolean = oldItem == newItem
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            R.layout.fruit_item_view -> GenusPillViewHolder(
                parent.viewBinding(
                    FruitItemViewBinding::inflate,
                    attachToParent = false
                )
            )
            else -> throw IllegalArgumentException("Not supported view type")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is GenusPillViewHolder -> holder.onBind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is GenusPickerData.Genus -> R.layout.fruit_item_view
            is GenusPickerData.All -> R.layout.fruit_item_view
        }
    }


    private inner class GenusPillViewHolder(private val binding: FruitItemViewBinding) :
        ViewHolder(binding.root) {

        fun onBind(genus: GenusPickerData) {

        }

    }
}