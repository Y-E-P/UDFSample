package com.pou.udfsample.ui.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pou.udfsample.R
import com.pou.udfsample.databinding.FruitItemViewBinding
import com.pou.udfsample.databinding.ViewErrorBinding
import com.pou.udfsample.databinding.ViewGenusSelectorBinding
import com.pou.udfsample.databinding.ViewLoadingBinding
import com.pou.udfsample.ui.main.common.getString
import com.pou.udfsample.ui.main.common.viewBinding
import com.pou.udfsample.utils.ListPaddingDecoration

class FruitsAdapter : ListAdapter<FruitsAdapterData, RecyclerView.ViewHolder>(diffCallback) {

    var clickCallback: () -> Unit = {}

    companion object {
        private val diffCallback: ItemCallback<FruitsAdapterData> =
            object : ItemCallback<FruitsAdapterData>() {
                override fun areItemsTheSame(
                    oldItem: FruitsAdapterData,
                    newItem: FruitsAdapterData
                ): Boolean {

                    val isSameData = oldItem is FruitsAdapterData.Data
                            && newItem is FruitsAdapterData.Data
                            && oldItem.id == newItem.id

                    val isSameSelected =
                        oldItem is FruitsAdapterData.GenusItems && newItem is FruitsAdapterData.GenusItems

                    val isError =
                        oldItem is FruitsAdapterData.Error && newItem is FruitsAdapterData.Error
                    val isLoading =
                        oldItem is FruitsAdapterData.Loading && newItem is FruitsAdapterData.Loading

                    return isSameData || isLoading || isError || isSameSelected
                }

                override fun areContentsTheSame(
                    oldItem: FruitsAdapterData,
                    newItem: FruitsAdapterData
                ): Boolean = oldItem == newItem
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.fruit_item_view -> FruitViewHolder(
                parent.viewBinding(
                    FruitItemViewBinding::inflate,
                    attachToParent = false
                )
            )

            R.layout.view_error -> ErrorViewHolder(
                parent.viewBinding(
                    ViewErrorBinding::inflate,
                    attachToParent = false
                )
            )

            R.layout.view_loading -> LoadingViewHolder(
                parent.viewBinding(
                    ViewLoadingBinding::inflate,
                    attachToParent = false
                )
            )

            R.layout.view_genus_selector -> GenusSelectorViewHolder(
                parent.viewBinding(
                    ViewGenusSelectorBinding::inflate,
                    attachToParent = false
                )
            )

            else -> throw IllegalArgumentException("Not supported view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FruitViewHolder -> holder.onBind(getItem(position) as FruitsAdapterData.Data)
            is ErrorViewHolder -> holder.onBind("Error")
            is GenusSelectorViewHolder -> holder.onBind(getItem(position) as FruitsAdapterData.GenusItems)
            is LoadingViewHolder -> {}
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is FruitsAdapterData.Data -> R.layout.fruit_item_view
            is FruitsAdapterData.Error -> R.layout.view_error
            is FruitsAdapterData.Loading -> R.layout.view_loading
            is FruitsAdapterData.GenusItems -> R.layout.view_genus_selector
        }
    }

    inner class FruitViewHolder(private val binding: FruitItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(fruitModel: FruitsAdapterData.Data) {
            binding.titleName.text = fruitModel.name
            binding.family.text = fruitModel.family
            binding.genus.text = fruitModel.genus

            binding.carbohydrates.text =
                getString(R.string.nutrition_carbohydrates, fruitModel.nutritions.carbohydrates)
            binding.protein.text =
                getString(R.string.nutrition_protein, fruitModel.nutritions.protein)
            binding.fat.text = getString(R.string.nutrition_fat, fruitModel.nutritions.fat)
            binding.calories.text =
                getString(R.string.nutrition_calories, fruitModel.nutritions.calories)
            binding.sugar.text = getString(R.string.nutrition_sugar, fruitModel.nutritions.sugar)
        }
    }

    inner class ErrorViewHolder(private val binding: ViewErrorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(errorMessage: String) {
            binding.errorText.text = errorMessage
        }

    }

    inner class LoadingViewHolder(binding: ViewLoadingBinding) :
        RecyclerView.ViewHolder(binding.root)

    inner class GenusSelectorViewHolder(private val binding: ViewGenusSelectorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter by lazy { GenusAdapter() }

        fun onBind(item: FruitsAdapterData.GenusItems) {
            if (binding.root.adapter == null) {
                binding.root.adapter = adapter
            }
            adapter.submitList(item.items)
            binding.root.addItemDecoration(
                ListPaddingDecoration(binding.root.context, left = 8, right = 8)
            )
        }

    }
}