package com.pou.udfsample.ui.main

import com.pou.udfsample.domain.NutritionModel

sealed class FruitsAdapterData {
    object Loading : FruitsAdapterData()
    object Error : FruitsAdapterData()
    data class Data(
        val id: Int,
        val genus: String,
        val name: String,
        val family: String,
        val order: String,
        val nutritions: NutritionModel
    ) : FruitsAdapterData()
    // data class GenusItems(val items: List<GenusPickerData>) : FruitsAdapterData()
}

sealed class GenusPickerData(open val isSelected: Boolean) {
    data class All(override val isSelected: Boolean) : GenusPickerData(isSelected)
    data class Genus(val name: String, override val isSelected: Boolean) :
        GenusPickerData(isSelected)
}