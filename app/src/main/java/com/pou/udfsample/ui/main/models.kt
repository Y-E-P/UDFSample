package com.pou.udfsample.ui.main

import com.pou.udfsample.domain.Fruit
import com.pou.udfsample.domain.FruitModel

sealed class FruitsAdapterData {
    object Loading : FruitsAdapterData()
    object Error : FruitsAdapterData()
    data class Data(
        val fruitModel: FruitModel,
    ) : FruitsAdapterData(), Fruit by fruitModel

    data class GenusItems(val items: List<GenusPickerData>) : FruitsAdapterData()
}

sealed class GenusPickerData(open val isSelected: Boolean) {
    data class All(override val isSelected: Boolean) : GenusPickerData(isSelected)
    data class Genus(val name: String, override val isSelected: Boolean) :
        GenusPickerData(isSelected)
}