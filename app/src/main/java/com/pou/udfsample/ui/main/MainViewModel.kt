package com.pou.udfsample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.pou.udfsample.domain.repo.FruitsRepository

class MainViewModel(
    /*private val savedState: SavedStateHandle,*/
    private val repository: FruitsRepository
) : ViewModel() {

    private var currentState: State = State()

    data class State(
        val data: List<FruitsAdapterData> = listOf(FruitsAdapterData.Loading),
        val genusList: List<String> = emptyList(),
        val selectedGenus: String = "All",
        val isLoading: Boolean = true
    )

    fun getAllFruitsList(): LiveData<State> {
        return liveData {
            emit(currentState.copy(isLoading = true))
            val data = repository.allFruitsList()
            currentState = currentState.copy(isLoading = false, data = data.map {
                FruitsAdapterData.Data(
                    it.id,
                    it.genus,
                    it.name,
                    it.family,
                    it.order,
                    it.nutritions,
                )
            })
            emit(currentState)
        }
    }

}