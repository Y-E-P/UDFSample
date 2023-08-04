package com.pou.udfsample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pou.udfsample.domain.FruitModel
import com.pou.udfsample.domain.repo.FruitsRepository
import kotlinx.coroutines.delay

class MainViewModel(
    private val repository: FruitsRepository
) : ViewModel() {

    private var currentState: State = State()
    private var rawData: List<FruitModel> = emptyList()

    data class State(
        val data: List<FruitsAdapterData> = listOf(FruitsAdapterData.Loading),
        val genusList: List<GenusPickerData> = emptyList(),
        val selectedGenus: GenusPickerData = GenusPickerData.All(true),
        val isLoading: Boolean = true
    )

    fun getAllFruitsList(): LiveData<State> {
        return liveData {
            emit(currentState.copy(isLoading = true))
            rawData = repository.allFruitsList()
            val data = rawData.map {
                FruitsAdapterData.Data(it)
            }
            val genus = listOf(GenusPickerData.All(true)) + data.map {
                GenusPickerData.Genus(
                    it.genus,
                    isSelected = false
                )
            }.toList()
            val genusData = FruitsAdapterData.GenusItems(genus)
            val resultData: List<FruitsAdapterData> = listOf(genusData) + data
            currentState =
                currentState.copy(genusList = genus, isLoading = false, data = resultData)
            emit(currentState)
        }
    }

    fun filterFruit(genus: GenusPickerData.Genus) : LiveData<State>{
        return liveData {
            emit(currentState.copy(isLoading = true))
            val data = rawData.filter { it.genus == genus.name }.map {
                FruitsAdapterData.Data(it)
            }
            val genusList = listOf(GenusPickerData.All(true)) + data.map {
                GenusPickerData.Genus(
                    it.genus,
                    isSelected = false
                )
            }.toList()
            val genusData = FruitsAdapterData.GenusItems(genusList)
            val resultData: List<FruitsAdapterData> = listOf(genusData) + data
            currentState =
                currentState.copy(genusList = genusList, isLoading = false, data = resultData)
            emit(currentState)
        }
    }

}