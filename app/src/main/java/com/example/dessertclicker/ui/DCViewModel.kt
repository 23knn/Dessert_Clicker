package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DCViewModel:ViewModel(){
    private val _uiState = MutableStateFlow(DCUIState())
    val uiState : StateFlow<DCUIState> = _uiState.asStateFlow()
    var desserts: List<Dessert> = Datasource.dessertList

    init{
        resetClicker()
    }

    fun resetClicker(){
        _uiState.value = DCUIState(
            0,
            0,
            0,
            0,
            desserts[0].imageId
        )
    }

    fun updatePriceAndSoldCount(){
        // Update the revenue
        // revenue += currentDessertPrice
        // dessertsSold++
    }

    fun showNextDessert(){
        // val dessertToShow = determineDessertToShow(desserts, dessertsSold)
        // currentDessertImageId = dessertToShow.imageId
        // currentDessertPrice = dessertToShow.price
    }
}